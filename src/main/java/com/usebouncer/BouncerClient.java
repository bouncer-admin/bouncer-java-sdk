package com.usebouncer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.usebouncer.exception.BadRequestException;
import com.usebouncer.exception.BouncerException;
import com.usebouncer.exception.IncorrectApiKeyException;
import com.usebouncer.exception.MissingApiKeyException;
import com.usebouncer.exception.NotEnoughCreditsException;
import com.usebouncer.exception.TooManyRequestsException;
import com.usebouncer.mapper.JacksonJsonParser;
import com.usebouncer.mapper.JsonParser;
import com.usebouncer.model.BatchStatus;
import com.usebouncer.model.Credits;
import com.usebouncer.model.EmailRecord;
import com.usebouncer.model.EmailRequest;
import com.usebouncer.model.ErrorResponse;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

public class BouncerClient implements AutoCloseable {

    private static volatile BouncerClient instance;

    public static BouncerClient getInstance() {
        if (instance == null) {
            synchronized (BouncerClient.class) {
                if (instance == null) {
                    instance = new BouncerClient();
                }
            }
        }
        return instance;
    }

    private static final int MAX_BATCH_SYNC_SIZE = 10000;
    private static final int MAX_CREATE_BATCH_SIZE = 100000;
    private final CloseableHttpClient httpClient;
    private String apiKey;
    private String baseUrl;
    private JsonParser parser;

    private BouncerClient() {
        this.httpClient = HttpClients.createDefault();
        this.baseUrl = "https://api.usebouncer.com";
        this.parser = new JacksonJsonParser();
    }

    public synchronized BouncerClient withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    protected synchronized BouncerClient withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    @Override
    public void close() throws Exception {
        this.httpClient.close();
    }

    // CREDITS
    public Credits getCredits() throws IOException {
        String uri = baseUrl + "/v1.1/credits";
        HttpGet request = new HttpGet(uri);

        return sendRequest(request, Credits.class);
    }

    // REAL TIME
    public EmailRecord verifyEmail(String email) throws IOException {
        return verifyEmail(email, null);
    }

    public EmailRecord verifyEmail(String email, Integer timeout) throws IOException {
        StringBuilder uriBuilder = new StringBuilder(baseUrl + "/v1.1/email/verify?email=" + email);
        if (timeout != null) {
            uriBuilder.append("&timeout=").append(timeout);
        }
        HttpGet request = new HttpGet(uriBuilder.toString());

        return sendRequest(request, EmailRecord.class);
    }

    // BATCH
    public BatchStatus createBatch(List<EmailRequest> requests) throws IOException {
        return createBatch(requests, null);
    }

    public BatchStatus createBatch(List<EmailRequest> requests, String callback) throws IOException {
        if (requests.size() > MAX_CREATE_BATCH_SIZE) {
            throw new BadRequestException("single create batch request can hold up to 10,000 emails");
        }
        StringBuilder uriBuilder = new StringBuilder(baseUrl + "/v1.1/email/verify/batch");
        if (callback != null) {
            uriBuilder.append("&callback=").append(callback);
        }
        HttpPost request = new HttpPost(uriBuilder.toString());

        String requestBody = parser.stringify(requests);
        request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

        return sendRequest(request, BatchStatus.class);
    }

    public BatchStatus checkStatus(String batchId) throws IOException {
        return checkStatus(batchId, false);
    }

    public BatchStatus checkStatus(String batchId, boolean withStats) throws IOException {
        String uri = baseUrl + "/v1.1/email/verify/batch/" + batchId + "?with-stats=" + withStats;
        HttpGet request = new HttpGet(uri);

        return sendRequest(request, BatchStatus.class);
    }

    public void deleteBatch(String batchId) throws IOException {
        String uri = baseUrl + "/v1.1/email/verify/batch/" + batchId;
        HttpDelete request = new HttpDelete(uri);

        sendRequest(request);
    }

    public void finishBatch(String batchId) throws IOException {
        String uri = baseUrl + "/v1.1/email/verify/batch/" + batchId + "/finish";
        HttpPost request = new HttpPost(uri);

        sendRequest(request);
    }

    public List<EmailRecord> downloadBatchResults(String batchId) throws IOException {
        return downloadBatchResults(batchId, "all");
    }

    public List<EmailRecord> downloadBatchResults(String batchId, String download) throws IOException {
        String uri = baseUrl + "/v1.1/email/verify/batch/" + batchId + "/download?download=" + download;
        HttpGet request = new HttpGet(uri);

        TypeReference<List<EmailRecord>> typeReference = new TypeReference<List<EmailRecord>>() {
        };

        return sendRequest(request, json -> parser.parse(json, typeReference));
    }

    // BATCH SYNC
    public List<EmailRecord> syncBatch(List<String> emails) throws IOException {
        if (emails.size() > MAX_BATCH_SYNC_SIZE) {
            throw new BadRequestException("single batch sync request can hold up to 10,000 emails");
        }
        String url = baseUrl + "/v1.1/email/verify/batch/sync";
        HttpPost request = new HttpPost(url);

        String requestBody = parser.stringify(emails);
        request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

        TypeReference<List<EmailRecord>> typeReference = new TypeReference<List<EmailRecord>>() {
        };

        return sendRequest(request, typeReference);
    }

    private void sendRequest(HttpUriRequestBase request) throws IOException {
        sendRequest(request, json -> null);
    }

    private <T> T sendRequest(HttpUriRequestBase request, Class<T> responseType) throws IOException {
        return sendRequest(request, json -> parser.parse(json, responseType));
    }

    private <T> T sendRequest(HttpUriRequestBase request, TypeReference<T> responseType) throws IOException {
        return sendRequest(request, json -> parser.parse(json, responseType));
    }

    private <T> T sendRequest(HttpUriRequestBase request, Function<String, T> resultResolver) throws IOException {
        HttpClientResponseHandler<T> responseHandler = response -> {
            String json = EntityUtils.toString(response.getEntity());
            if (response.getCode() > 299) {
                throw toException(json);
            } else {
                return resultResolver.apply(json);
            }
        };

        request.setHeader("x-api-key", apiKey);
        request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        return httpClient.execute(request, responseHandler);
    }

    private BouncerException toException(String json) {
        ErrorResponse error = parser.parseError(json);
        if (error.getStatus() == 400 && "batch has to be running to finish".equals(error.getMessage())) {
            return new BadRequestException(error.getMessage());
        }
        if (error.getStatus() == 401 && "api key is required".equals(error.getMessage())) {
            return new MissingApiKeyException(error.getMessage());
        }
        if (error.getStatus() == 401 && "user not found".equals(error.getMessage())) {
            return new IncorrectApiKeyException("incorrect api key");
        }
        if (error.getStatus() == 402) {
            return new NotEnoughCreditsException("not enough credits");
        }
        if (error.getStatus() == 405 && "email addresses and results have been removed".equals(error.getMessage())) {
            return new BadRequestException(error.getMessage());
        }
        if (error.getStatus() == 429) {
            return new TooManyRequestsException("request was rate limited");
        }
        return new BouncerException(error.getMessage());
    }

}

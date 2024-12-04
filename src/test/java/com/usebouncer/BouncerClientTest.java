package com.usebouncer;

import com.usebouncer.model.BatchStatus;
import com.usebouncer.model.Credits;
import com.usebouncer.model.EmailRecord;
import com.usebouncer.model.EmailRequest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BouncerClientTest {

    private static final String API_KEY = "";
    private static final String BASE_URL = "https://api.usebouncer.com";

    private final BouncerClient client = BouncerClient.getInstance()
            .withBaseUrl(BASE_URL)
            .withApiKey(API_KEY);

    @Disabled
    @Test
    public void shouldCheckCredits() {
        try {
            Credits credits = client.getCredits();
            assertTrue(credits.getCredits() > 0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Disabled
    @Test
    public void shouldVerifyEmail() {
        try {
            EmailRecord emailRecord = client.verifyEmail("hello@usebouncer.com");
            assertEquals("deliverable", emailRecord.getStatus());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Disabled
    @Test
    public void shouldSyncBatch() {
        try {
            List<String> emails = Collections.singletonList("hello@usebouncer.com");
            List<EmailRecord> emailRecords = client.syncBatch(emails);
            System.out.println(emailRecords);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Disabled
    @Test
    public void shouldCreateBatch() {
        try {
            List<EmailRequest> emails = Stream.of("hello@usebouncer.com")
                    .map(email -> new EmailRequest(email, null))
                    .collect(Collectors.toList());
            BatchStatus batchStatus = client.createBatch(emails);
            System.out.println(batchStatus);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Disabled
    @Test
    public void shouldCheckBatchStatus() {
        try {
            BatchStatus batchStatus = client.checkStatus("", true);
            System.out.println(batchStatus);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Disabled
    @Test
    public void shouldDownloadResults() {
        try {
            List<EmailRecord> emailRecords = client.downloadBatchResults("");
            System.out.println(emailRecords);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Disabled
    @Test
    public void shouldFinishBatch() {
        try {
            client.finishBatch("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Disabled
    @Test
    public void shouldDeleteBatch() {
        try {
            client.deleteBatch("");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

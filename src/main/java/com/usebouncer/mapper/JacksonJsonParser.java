package com.usebouncer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.usebouncer.exception.ParameterConversionException;
import com.usebouncer.model.ErrorResponse;

import java.io.IOException;
import java.util.Map;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;

public class JacksonJsonParser implements JsonParser {

    private static final ObjectMapper objectMapper = objectMapper();

    public <T> T parse(String json, Class<T> responseType) {
        try {
            return objectMapper.readValue(json, responseType);
        } catch (IOException e) {
            throw new ParameterConversionException("failed to deserialize json object " + json);
        }

    }

    public <T> T parse(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new ParameterConversionException("failed to deserialize json object " + json);
        }
    }

    public <T> String stringify(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new ParameterConversionException("failed to serialize object " + obj + " to string");
        }
    }

    public ErrorResponse parseError(String json) {
        try {
            Map<String, Object> stringObjectMap = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
            return ErrorResponse.parse(stringObjectMap);
        } catch (IOException e) {
            throw new ParameterConversionException("failed to deserialize json object " + json);
        }
    }

    private static ObjectMapper objectMapper() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .registerModule(new JavaTimeModule())
                .enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES);
    }

}

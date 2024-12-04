package com.usebouncer.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.usebouncer.model.ErrorResponse;


public interface JsonParser {

    <T> T parse(String json, Class<T> responseType);

    <T> T parse(String json, TypeReference<T> typeReference);

    <T> String stringify(T obj);

    ErrorResponse parseError(String json);
}

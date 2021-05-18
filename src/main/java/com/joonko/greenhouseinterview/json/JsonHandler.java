package com.joonko.greenhouseinterview.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * A json serializer and deserializer
 */
public class JsonHandler<T> {

    ObjectMapper objectMapper = new ObjectMapper();
    JavaType type;

    public JsonHandler(Class<T> clazz) {
        this.type = objectMapper.getTypeFactory().
                constructType(clazz);
    }

    public JsonHandler(TypeReference<T> typeReference) {
        type = objectMapper.getTypeFactory().constructType(typeReference);
    }

    public T deserialize(String json) throws IOException {
        return objectMapper.readerFor(type).readValue(json);
    }

    public String serialize(T t) throws IOException {
        return objectMapper.writerFor(type).writeValueAsString(t);
    }
}
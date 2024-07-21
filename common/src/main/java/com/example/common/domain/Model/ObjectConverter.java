package com.example.common.domain.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

@Converter
public class ObjectConverter implements AttributeConverter<Object, String> {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(Object o) {
        return MAPPER.writeValueAsString(o);
    }

    @Override
    public Object convertToEntityAttribute(String s) {
        return MAPPER.convertValue(s, Object.class);
    }
}

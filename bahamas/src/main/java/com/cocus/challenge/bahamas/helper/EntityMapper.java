package com.cocus.challenge.bahamas.helper;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EntityMapper {

    public static <T> Map<String, String> entityToStringMap(T entity) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> map = objectMapper.convertValue(entity, new TypeReference<Map<String, String>>() {
        });

        return map;
    }
}

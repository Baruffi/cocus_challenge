package com.cocus.challenge.bahamas.interfaces;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public interface IRequestHandler {

    // Only external post requests are required for this service.
    public <T> ResponseEntity<T> post(String url, HttpEntity<T> request, Map<String, String> uriVariables, Class<T> clazz);
}

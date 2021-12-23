package com.cocus.challenge.bahamas.helper;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHandler {

    private RestTemplate restTemplate;

    @Autowired
    public RequestHandler(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public <T> ResponseEntity<T> post(String url, HttpEntity<T> request, Map<String, String> uriVariables, Class<T> clazz) {
        return restTemplate.postForEntity(url, request, clazz, uriVariables);
    }
}

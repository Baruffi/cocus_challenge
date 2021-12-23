package com.cocus.challenge.bahamas.helper;

import java.util.Map;

import com.cocus.challenge.bahamas.interfaces.IRequestHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RequestHandler implements IRequestHandler {

    private RestTemplate restTemplate;

    @Autowired
    public RequestHandler(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public <T> ResponseEntity<T> post(String url, HttpEntity<T> request, Map<String, String> uriVariables, Class<T> clazz) {
        return restTemplate.postForEntity(url, request, clazz, uriVariables);
    }
}

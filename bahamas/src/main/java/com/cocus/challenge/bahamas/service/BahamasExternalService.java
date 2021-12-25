package com.cocus.challenge.bahamas.service;

import java.util.Map;

import com.cocus.challenge.bahamas.enums.ExternalServiceCallResolution;
import com.cocus.challenge.bahamas.helper.EntityMapper;
import com.cocus.challenge.bahamas.interfaces.IExternalService;
import com.cocus.challenge.bahamas.interfaces.IRequestHandler;
import com.cocus.challenge.bahamas.model.InvoiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BahamasExternalService implements IExternalService<InvoiceClient> {

    @Autowired
    IRequestHandler requestHandler;

    @Value("${bahamas.bahamasUrl}")
    private String bahamasUrl;

    private ResponseEntity<String> exchange(InvoiceClient client) {
        Map<String, String> map = EntityMapper.entityToStringMap(client);

        ResponseEntity<String> response = requestHandler.post(bahamasUrl, null, map, String.class);

        return response;
    }

    @Override
    public ExternalServiceCallResolution call(InvoiceClient client) {
        ResponseEntity<String> response = exchange(client);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ExternalServiceCallResolution.SUCCESS;
        }

        return ExternalServiceCallResolution.FAILURE;
    }
}

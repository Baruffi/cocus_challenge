package com.cocus.challenge.bahamas.service;

import com.cocus.challenge.bahamas.enums.ExternalServiceCallResolution;
import com.cocus.challenge.bahamas.interfaces.IExternalService;
import com.cocus.challenge.bahamas.model.InvoiceClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BahamasExternalService implements IExternalService<InvoiceClient> {

    @Value(value = "bahamasUrl")
    private String bahamasUrl;

    @Override
    public ExternalServiceCallResolution call(InvoiceClient client) {
        // TODO: call to external url https://bahamas.gov/register?invoice=1234&invoice_id=999999999&name=Bob&email=bob@bob.com

        return ExternalServiceCallResolution.FAILURE;
    }
}

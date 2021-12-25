package com.cocus.challenge.bahamas.service;

import com.cocus.challenge.bahamas.interfaces.IStorageService;
import com.cocus.challenge.bahamas.model.InvoiceClient;
import com.cocus.challenge.bahamas.repo.InvoiceClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceClientStorageService implements IStorageService<InvoiceClient> {

    @Autowired
    private InvoiceClientRepository repository;

    @Override
    public InvoiceClient get(Long invoiceId) {
        InvoiceClient client = repository.findById(invoiceId).get();

        return client;
    }

    @Override
    public void save(InvoiceClient client) {
        repository.save(client);
    }
}

package com.cocus.challenge.bahamas.controller;

import com.cocus.challenge.bahamas.enums.ExternalServiceCallResolution;
import com.cocus.challenge.bahamas.interfaces.IExternalService;
import com.cocus.challenge.bahamas.interfaces.IStorageService;
import com.cocus.challenge.bahamas.model.InvoiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private IStorageService<InvoiceClient> clientService;

    @Autowired
    private IExternalService<InvoiceClient> bahamasService;

    @GetMapping("/retrieve-bahamas-client/{invoiceId}")
    public InvoiceClient getClient(@PathVariable Long invoiceId) {
        return clientService.get(invoiceId);
    }

    @PostMapping("/store-bahamas-client/{invoiceId}")
    public void storeClient(@PathVariable Long invoiceId, @RequestParam String fiscalId, @RequestParam String name,
            @RequestParam String email) {
        InvoiceClient client = new InvoiceClient(invoiceId, fiscalId, name, email);

        ExternalServiceCallResolution resolution = bahamasService.call(client);

        if (ExternalServiceCallResolution.SUCCESS.equals(resolution)) {
            clientService.save(client);
        }
    }
}

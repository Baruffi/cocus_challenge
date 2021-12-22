package com.cocus.challenge.bahamas.service;

import com.cocus.challenge.bahamas.model.InvoiceClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class BahamasExternalServiceTest {

    private static final long ID = 1L;
    private static final String FISCAL_ID = "99999999";
    private static final String NAME = "Bob";
    private static final String EMAIL = "bob@bob.com";

    @Autowired
    private BahamasExternalService service;

    private InvoiceClient client;

    @BeforeEach
    void setup() {
        client = new InvoiceClient(ID, FISCAL_ID, NAME, EMAIL);
    }

    @Test
    void testCall() {
        // service.call(client);
    }
}

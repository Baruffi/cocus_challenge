package com.cocus.challenge.bahamas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cocus.challenge.bahamas.model.InvoiceClient;
import com.cocus.challenge.bahamas.repo.InvoiceClientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class InvoiceClientStorageServiceTest {

    private static final long ID = 1L;
    private static final String FISCAL_ID = "99999999";
    private static final String NAME = "Bob";
    private static final String EMAIL = "bob@bob.com";

    @Autowired
    @InjectMocks
    private InvoiceClientStorageService service;

    @Mock
    private InvoiceClientRepository repository;

    private InvoiceClient client;

    @BeforeEach
    void setup() {
        client = new InvoiceClient(ID, FISCAL_ID, NAME, EMAIL);
    }

    @Test
    void testGet() {
        when(repository.getById(ID)).thenReturn(client);

        InvoiceClient clientGet = service.get(ID);

        assertEquals(client, clientGet);
    }

    @Test
    void testSave() {
        service.save(client);

        verify(repository, times(1)).save(client);
    }
}

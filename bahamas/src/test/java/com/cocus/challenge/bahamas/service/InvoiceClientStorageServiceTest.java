package com.cocus.challenge.bahamas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cocus.challenge.bahamas.model.InvoiceClient;
import com.cocus.challenge.bahamas.repo.InvoiceClientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = InvoiceClientStorageService.class)
public class InvoiceClientStorageServiceTest {

    private static final long ID = 1L;
    private static final String FISCAL_ID = "99999999";
    private static final String NAME = "Bob";
    private static final String EMAIL = "bob@bob.com";

    @Autowired
    @InjectMocks
    private InvoiceClientStorageService service;

    @MockBean
    private InvoiceClientRepository repository;

    private InvoiceClient client;

    @BeforeEach
    void setup() {
        client = new InvoiceClient(ID, FISCAL_ID, NAME, EMAIL);
    }

    @Test
    void getsFromRepository() {
        when(repository.getById(ID)).thenReturn(client);

        InvoiceClient clientGet = service.get(ID);

        assertEquals(client, clientGet);
    }

    @Test
    void savesToRepository() {
        service.save(client);

        verify(repository, times(1)).save(client);
    }
}

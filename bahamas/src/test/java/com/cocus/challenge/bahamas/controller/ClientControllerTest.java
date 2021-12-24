package com.cocus.challenge.bahamas.controller;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import com.cocus.challenge.bahamas.enums.ExternalServiceCallResolution;
import com.cocus.challenge.bahamas.helper.EntityMapper;
import com.cocus.challenge.bahamas.interfaces.IExternalService;
import com.cocus.challenge.bahamas.interfaces.IStorageService;
import com.cocus.challenge.bahamas.model.InvoiceClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    private static final long ID = 1L;
    private static final String FISCAL_ID = "99999999";
    private static final String NAME = "Bob";
    private static final String EMAIL = "bob@bob.com";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @InjectMocks
    ClientController clientController;

    @MockBean
    private IStorageService<InvoiceClient> clientService;

    @MockBean
    private IExternalService<InvoiceClient> bahamasService;

    private InvoiceClient client;

    @BeforeEach
    void setup() {
        client = new InvoiceClient(ID, FISCAL_ID, NAME, EMAIL);
    }

    @Test
    void testGetClient() throws Exception {
        when(clientService.get(ID)).thenReturn(client);

        mockMvc.perform(get("/retrieve-bahamas-client/" + ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(NAME)))
                .andExpect(jsonPath("$.email", is(EMAIL)))
                .andExpect(jsonPath("$.fiscalId", is(FISCAL_ID)))
                .andExpect(jsonPath("$.invoiceId", comparesEqualTo((int) ID)));
    }

    @Test
    void testStoreClient() throws Exception {
        when(bahamasService.call(client)).thenReturn(ExternalServiceCallResolution.SUCCESS);

        Map<String, String> map = EntityMapper.entityToStringMap(client);

        String uri = UriComponentsBuilder
                .fromUriString("/store-bahamas-client/" + ID)
                .query("fiscal_id={fiscalId}&name={name}&email={email}")
                .buildAndExpand(map)
                .toUriString();

        mockMvc.perform(post(uri))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

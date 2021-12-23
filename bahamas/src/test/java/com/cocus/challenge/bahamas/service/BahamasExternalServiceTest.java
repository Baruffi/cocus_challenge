package com.cocus.challenge.bahamas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;

import com.cocus.challenge.bahamas.enums.ExternalServiceCallResolution;
import com.cocus.challenge.bahamas.helper.EntityMapper;
import com.cocus.challenge.bahamas.helper.RequestHandler;
import com.cocus.challenge.bahamas.model.InvoiceClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = BahamasExternalService.class)
public class BahamasExternalServiceTest {

    private static final long ID = 1L;
    private static final String FISCAL_ID = "99999999";
    private static final String NAME = "Bob";
    private static final String EMAIL = "bob@bob.com";

    @Autowired
    @InjectMocks
    private BahamasExternalService service;

    @MockBean
    private RequestHandler requestHandler;

    private InvoiceClient client;

    @BeforeEach
    void setup() {
        client = new InvoiceClient(ID, FISCAL_ID, NAME, EMAIL);
    }

    @Test
    void succeedsOnCallResolutionWhenResponseIsOk() {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);
        Map<String, String> map = EntityMapper.entityToStringMap(client);

        when(requestHandler.post(anyString(), any(), eq(map), eq(Object.class))).thenReturn(response);

        ExternalServiceCallResolution resolution = service.call(client);

        assertEquals(ExternalServiceCallResolution.SUCCESS, resolution);
    }

    @Test
    void failsOnCallResolutionWhenResponseIsNotOk() {
        ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Arbitrary not ok response
        Map<String, String> map = EntityMapper.entityToStringMap(client);

        when(requestHandler.post(anyString(), any(), eq(map), eq(Object.class))).thenReturn(response);

        ExternalServiceCallResolution resolution = service.call(client);

        assertEquals(ExternalServiceCallResolution.FAILURE, resolution);
    }
}

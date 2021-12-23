package com.cocus.challenge.bahamas.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = {RequestHandler.class, RequestHandlerTest.ContextConfiguration.class})
public class RequestHandlerTest {

    @TestConfiguration
    static class ContextConfiguration {

        @Bean
        public RestTemplateBuilder restTemplateBuilder() {
            RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
            RestTemplate restTemplate = mock(RestTemplate.class);

            when(restTemplateBuilder.build()).thenReturn(restTemplate);

            return restTemplateBuilder;
        }
    }

    @Autowired
    private RequestHandler requestHandler;

    @Autowired
    private TestRestTemplate testRestTemplate;

    ResponseEntity<Object> response;

    @BeforeEach
    void setup() {
        response = new ResponseEntity<>(HttpStatus.OK);
    }

    @Test
    void testPost() {
        RestTemplate restTemplate = testRestTemplate.getRestTemplate();

        // anyMap() doesn't allow null values and any() makes the method call ambiguous, this is why nullable(Map.class) is used
        when(restTemplate.postForEntity(anyString(), nullable(Object.class), eq(Object.class), nullable(Map.class))).thenReturn(response);

        ResponseEntity<Object> requestResponse = requestHandler.post("", null, null, Object.class);

        assertEquals(response, requestResponse);
    }
}

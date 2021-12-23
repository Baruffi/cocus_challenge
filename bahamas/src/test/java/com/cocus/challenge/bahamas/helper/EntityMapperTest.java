package com.cocus.challenge.bahamas.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.util.Map;

import com.cocus.challenge.bahamas.model.InvoiceClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EntityMapperTest {

    private static final long ID = 1L;
    private static final String FISCAL_ID = "99999999";
    private static final String NAME = "Bob";
    private static final String EMAIL = "bob@bob.com";

    private InvoiceClient client;

    @BeforeEach
    void setup() {
        client = new InvoiceClient(ID, FISCAL_ID, NAME, EMAIL);
    }

    @Test
    void convertsEntityToStringMap() throws IllegalArgumentException, IllegalAccessException {
        Map<String, String> map = EntityMapper.entityToStringMap(client);

        for (Field field : client.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String key = field.getName();
            String value = field.get(client).toString();

            assertEquals(value, map.get(key));
        }
    }
}

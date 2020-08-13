package com.homework.nix.service.impl;

import com.homework.nix.data.AppProperties;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldInitializerTest {

    FieldInitializerImpl fieldInitializer = new FieldInitializerImpl();

    @Test
    void initialize() throws Exception {
        AppProperties appProperties = new AppProperties();
        double heightBeforeInitialize = appProperties.height;
        fieldInitializer.initialize(appProperties);
        double heightAfterInitialize = appProperties.height;
        assertNotEquals(heightBeforeInitialize, heightAfterInitialize);
    }

    @Test
    void isFieldPresentInProperties() {
        String propertyKey = "name";
        assertTrue(fieldInitializer.isFieldPresentInProperties(propertyKey));
    }

    @Test
    void getProperty() {
        int age = 37;
        String stringAge = "age";
        assertEquals(age, fieldInitializer.getValueByKeyInProperties(stringAge));
    }

    @Test
    void getTypeOfNumeric() {
        String stringDoubleNumber = "1.23";
        double doubleNumber = 1.23;
        assertEquals(doubleNumber, fieldInitializer.getConvertedVariable(stringDoubleNumber));
    }
}
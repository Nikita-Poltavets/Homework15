package com.homework.nix.service.impl;

import com.homework.nix.data.AppProperties;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class FieldInitializerTest {

    FieldInitializerImpl fieldInitializer = new FieldInitializerImpl();

    @Test
    void initialize() {

        try(FileReader reader = new FileReader("app.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            String valueNameFromPropertiesFile = properties.getProperty("name");
            AppProperties appProperties = new AppProperties();
            fieldInitializer.initialize(appProperties);
            String fieldNameAfterInitialize = appProperties.name;
            assertEquals(fieldNameAfterInitialize, valueNameFromPropertiesFile);
        }
        catch (Exception ignored){}
    }


    @Test
    void getTypeOfNumeric() {
        String stringDoubleNumber = "1.23";
        double doubleNumber = 1.23;
        assertEquals(doubleNumber, fieldInitializer.getConvertedVariable(stringDoubleNumber));
    }
}
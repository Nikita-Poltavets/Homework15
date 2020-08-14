package com.homework.nix.service.impl;

import com.homework.nix.annotation.PropertyKey;
import com.homework.nix.service.FieldInitializer;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class FieldInitializerImpl implements FieldInitializer {

    @Override
    public void initialize(Object object) throws Exception {
        try(FileReader reader = new FileReader("app.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            boolean isFieldPresentInProperties;
            String getValueByKeyInProperties;
        Class<?> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                isFieldPresentInProperties = properties.containsKey(propertyKey.value());
                if(isFieldPresentInProperties){
                    getValueByKeyInProperties = properties.getProperty(propertyKey.value());
                    field.set(object, getConvertedVariable(getValueByKeyInProperties));
                }
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getConvertedVariable(String field) {
        try {
            Integer integerNumber = Integer.parseInt(field);
            return integerNumber;
        } catch (NumberFormatException ignored) {}
        try {
            Double doubleNumber = Double.parseDouble(field);
            return doubleNumber;
        } catch (NumberFormatException ignored) {}
        return field;
    }
}

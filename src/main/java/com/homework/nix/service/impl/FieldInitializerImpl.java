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
        Class<?> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(PropertyKey.class)) {
                PropertyKey propertyKey = field.getAnnotation(PropertyKey.class);
                if(isFieldPresentInProperties(propertyKey.value())){
                    field.set(object, getValueByKeyInProperties(propertyKey.value()));
                }
            }
        }
    }

    @Override
    public Boolean isFieldPresentInProperties(String field){
        try(FileReader reader = new FileReader("app.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            Boolean isContainsField = properties.containsKey(field);
            return isContainsField;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object getValueByKeyInProperties(String field){
        try(FileReader reader = new FileReader("app.properties")) {
            Properties properties = new Properties();
            properties.load(reader);
            String url = properties.getProperty(field);
            return getConvertedVariable(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

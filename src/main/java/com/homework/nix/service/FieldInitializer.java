package com.homework.nix.service;

public interface FieldInitializer {

    void initialize(Object object) throws Exception;

    Boolean isFieldPresentInProperties(String field);

    Object getValueByKeyInProperties(String field);

    Object getConvertedVariable(String field);
}

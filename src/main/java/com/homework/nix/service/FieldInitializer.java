package com.homework.nix.service;

public interface FieldInitializer {

    void initialize(Object object) throws Exception;

    Object getConvertedVariable(String field);
}

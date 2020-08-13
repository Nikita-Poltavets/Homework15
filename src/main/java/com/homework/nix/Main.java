package com.homework.nix;

import com.homework.nix.data.AppProperties;
import com.homework.nix.service.FieldInitializer;
import com.homework.nix.service.impl.FieldInitializerImpl;


public class Main {

    public static void main(String[] args) {
        FieldInitializer fieldInitializer = new FieldInitializerImpl();
        AppProperties appProperties = new AppProperties();
        try {
            System.out.println("Before initialize - " + appProperties.age);
            fieldInitializer.initialize(appProperties);
            System.out.println("After initialize - " + appProperties.age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

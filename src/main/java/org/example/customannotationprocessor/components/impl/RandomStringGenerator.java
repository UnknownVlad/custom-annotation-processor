package org.example.customannotationprocessor.components.impl;

import org.example.customannotationprocessor.components.interfaces.RandomGenerator;

import java.util.UUID;

public class RandomStringGenerator implements RandomGenerator<String> {
    @Override
    public String generateValue() {
        return UUID.randomUUID().toString();
    }
}

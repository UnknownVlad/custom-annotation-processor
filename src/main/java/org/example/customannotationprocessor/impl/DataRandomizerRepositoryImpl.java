package org.example.customannotationprocessor.impl;

import org.example.customannotationprocessor.components.annotations.RandomizerRepository;
import org.example.customannotationprocessor.components.interfaces.DataRandomizerRepository;
import org.example.customannotationprocessor.pojos.UserPojo;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

@RandomizerRepository
public class DataRandomizerRepositoryImpl implements DataRandomizerRepository<UserPojo> {

    @Override
    public UserPojo generate() {
        return null;
    }
}

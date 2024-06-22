package org.example.customannotationprocessor.components.annotations.processors;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;


public interface RandomizerAnnotationProcessor {

    void process(Object object, Field field);

}

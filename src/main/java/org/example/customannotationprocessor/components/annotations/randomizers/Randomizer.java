package org.example.customannotationprocessor.components.annotations.randomizers;

import org.example.customannotationprocessor.components.interfaces.RandomGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Randomizer {
    Class<? extends RandomGenerator<?>> generator();
}

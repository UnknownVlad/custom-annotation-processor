package org.example.customannotationprocessor.components.interfaces;

public interface DataRandomizerRepository<T> {
    default T generate(){
        return null;
    }

}

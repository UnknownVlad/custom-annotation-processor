package org.example.customannotationprocessor.components.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtils {
    public static List<Type> getGenericTypes(Class<?> beanClass) {
        List<Type> genericTypes = new ArrayList<>();
        Type[] genericInterfaces = beanClass.getGenericInterfaces();
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType parameterizedType) {
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments.length > 0) {
                    genericTypes.add(actualTypeArguments[0]);
                }
            }
        }
        return genericTypes;
    }

    public static Class<?> getClass(Type type) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        }
        throw new RuntimeException("Can't create Class by type");
    }
}

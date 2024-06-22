package org.example.customannotationprocessor.components.bpp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.customannotationprocessor.components.annotations.RandomizerRepository;
import org.example.customannotationprocessor.components.annotations.processors.RandomizerAnnotationProcessor;
import org.example.customannotationprocessor.components.utils.ReflectUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RandomizerRepositoryBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Class<?>> beanClasses = new ConcurrentHashMap<>();
    private final Map<String, Class<?>> beanGenericTypes = new ConcurrentHashMap<>();
    private final RandomizerAnnotationProcessor randomizerAnnotationProcessor;


    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(RandomizerRepository.class)) {
            Type type = ReflectUtils.getGenericTypes(beanClass).get(0);
            Class<?> clazz = ReflectUtils.getClass(type);
            beanGenericTypes.put(beanName, clazz);
            beanClasses.put(beanName, beanClass);
        }
        return bean;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beanClasses.get(beanName);

        if (Objects.nonNull(beanClass)) {
            Class<?> beanGenericType = beanGenericTypes.get(beanName);
            return Proxy.newProxyInstance(
                    beanClass.getClassLoader(),
                    beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        Object object = beanGenericType.getDeclaredConstructor().newInstance();
                        if (method.getName().equals("generate")) {
                            for (Field f : object.getClass().getDeclaredFields()) {
                                randomizerAnnotationProcessor.process(object, f);
                            }
                        }
                        return object;
                    }
            );
        }

        return bean;
    }
}

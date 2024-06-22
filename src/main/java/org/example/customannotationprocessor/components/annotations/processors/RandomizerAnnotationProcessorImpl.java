package org.example.customannotationprocessor.components.annotations.processors;

import lombok.SneakyThrows;
import org.example.customannotationprocessor.components.annotations.randomizers.RandomInteger;
import org.example.customannotationprocessor.components.annotations.randomizers.Randomizer;
import org.example.customannotationprocessor.components.interfaces.RandomGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;

@Component
public class RandomizerAnnotationProcessorImpl implements RandomizerAnnotationProcessor {
    @SneakyThrows
    @Override
    public void process(Object object, Field field) {
        if (field.getAnnotations().length == 0)
            return;

        if (field.isAnnotationPresent(Randomizer.class)) {
            Randomizer randomizerAnnotation = field.getAnnotation(Randomizer.class);
            Class<? extends RandomGenerator<?>> generatorClass = randomizerAnnotation.generator();
            RandomGenerator<?> generator = generatorClass.getDeclaredConstructor().newInstance();
            Object value = generator.generateValue();
            inject(field, object, value);
        } else if (field.isAnnotationPresent(RandomInteger.class)) {
            RandomInteger randomizerAnnotation = field.getAnnotation(RandomInteger.class);
            int max = randomizerAnnotation.max();
            int min = randomizerAnnotation.min();
            Object value = new Random().nextInt(max-min)+min;
            inject(field, object, value);
        }
    }

    private void inject(Field field, Object object, Object value){
        //todo: разобраться с доступностью полей
        field.setAccessible(true);
        ReflectionUtils.setField(field, object, value);
        field.setAccessible(true);
        ReflectionUtils.setField(field, object, value);
    }
}

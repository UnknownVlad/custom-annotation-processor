package org.example.customannotationprocessor.components.annotations.processors;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.customannotationprocessor.components.annotations.randomizers.RandomInteger;
import org.example.customannotationprocessor.components.annotations.randomizers.RandomStringPattern;
import org.example.customannotationprocessor.components.annotations.randomizers.Randomizer;
import org.example.customannotationprocessor.components.constants.EmailGeneratorConstants;
import org.example.customannotationprocessor.components.enums.PatternType;
import org.example.customannotationprocessor.components.interfaces.RandomGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RandomizerAnnotationProcessorImpl implements RandomizerAnnotationProcessor {
    private final EmailGeneratorConstants emailGeneratorConstants;

    @SneakyThrows
    @Override
    public void process(Object object, Field field) {
        if (field.getAnnotations().length == 0)
            return;
        Object value = null;
        if (field.isAnnotationPresent(Randomizer.class)) {
            Randomizer randomizerAnnotation = field.getAnnotation(Randomizer.class);
            Class<? extends RandomGenerator<?>> generatorClass = randomizerAnnotation.generator();
            RandomGenerator<?> generator = generatorClass.getDeclaredConstructor().newInstance();
            value = generator.generateValue();
        } else if (field.isAnnotationPresent(RandomInteger.class)) {
            RandomInteger randomizerAnnotation = field.getAnnotation(RandomInteger.class);
            int max = randomizerAnnotation.max();
            int min = randomizerAnnotation.min();
            value = new Random().nextInt(max - min) + min;
        } else if (field.isAnnotationPresent(RandomStringPattern.class)) {
            RandomStringPattern randomizerAnnotation = field.getAnnotation(RandomStringPattern.class);
            PatternType patternType = randomizerAnnotation.target();
            value = processPatternType(patternType);
        }
        inject(field, object, value);
    }

    private String processPatternType(PatternType patternType) {
        if (patternType.equals(PatternType.ID)) {
            return UUID.randomUUID().toString();
        } else if (patternType.equals(PatternType.PHONE_NUMBER)) {
            return "+7(" +
                    (new Random().nextInt(899) + 100) +
                    ")" +
                    (new Random().nextInt(899) + 100) +
                    "-" +
                    (new Random().nextInt(89) + 10) +
                    "-" +
                    (new Random().nextInt(89) + 10);
        } else if (patternType.equals(PatternType.EMAIL)) {
            return "generated.email@" +
                    emailGeneratorConstants.domains()[
                            new Random().nextInt(emailGeneratorConstants.domains().length)] +
                    "." +
                    emailGeneratorConstants.countryCodes()[
                            new Random().nextInt(emailGeneratorConstants.countryCodes().length)];
        }
        return null;
    }


    private void inject(Field field, Object object, Object value) {
        //todo: разобраться с доступностью полей
        field.setAccessible(true);
        ReflectionUtils.setField(field, object, value);
        field.setAccessible(true);
        ReflectionUtils.setField(field, object, value);
    }
}

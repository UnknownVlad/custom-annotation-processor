package org.example.customannotationprocessor.components.constants;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
//todo: не правильно вычитываются проперти, перепроверть
@ConfigurationProperties(prefix = "random.generator.email")
public record EmailGeneratorConstants(
        String[] domains,
        String[] countryCodes
) {
}

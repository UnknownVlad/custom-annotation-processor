package org.example.customannotationprocessor;

import org.example.customannotationprocessor.components.constants.EmailGeneratorConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {
        EmailGeneratorConstants.class
})
public class CustomAnnotationProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomAnnotationProcessorApplication.class, args);

    }

}

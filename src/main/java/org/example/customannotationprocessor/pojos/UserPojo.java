package org.example.customannotationprocessor.pojos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.customannotationprocessor.components.annotations.randomizers.RandomInteger;
import org.example.customannotationprocessor.components.annotations.randomizers.RandomStringPattern;
import org.example.customannotationprocessor.components.annotations.randomizers.Randomizer;
import org.example.customannotationprocessor.components.enums.PatternType;
import org.example.customannotationprocessor.components.impl.RandomStringGenerator;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserPojo {
    @RandomStringPattern(target = PatternType.PHONE_NUMBER)
    String phoneNumber;
    @RandomInteger(max = 30, min = 18)
    Integer age;
    @RandomStringPattern(target = PatternType.ID)
    String id;

    @RandomStringPattern(target = PatternType.EMAIL)
    String email;
}

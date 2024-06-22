package org.example.customannotationprocessor.pojos;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.customannotationprocessor.components.annotations.randomizers.RandomInteger;
import org.example.customannotationprocessor.components.annotations.randomizers.Randomizer;
import org.example.customannotationprocessor.components.impl.RandomStringGenerator;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserPojo {
    @Randomizer(generator = RandomStringGenerator.class)
    String name;

    @RandomInteger(max = 30, min = 18)
    Integer age;

    @Randomizer(generator = RandomStringGenerator.class)
    String email;
}

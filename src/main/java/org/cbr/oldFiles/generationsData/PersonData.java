package org.cbr.oldFiles.generationsData;


import com.github.javafaker.Faker;
import lombok.Data;

import java.util.Locale;


@Data
public class PersonData {
    Faker faker = new Faker(new Locale("ru", "RU"));
    String name = faker.name().fullName();

    int age = faker.number().numberBetween(19, 65);
    String email = faker.bothify("????##@mail.ru");
}

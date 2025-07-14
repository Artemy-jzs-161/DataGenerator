package org.cbr;

import org.cbr.generator.*;
import org.cbr.model.Individual;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppConfig {
    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
    }

    @Bean
    public CommandLineRunner demo(IndividualGenerator generator) {
        return args -> {
            System.out.println("Генерация и сохранение данных...");
            for (int i = 0; i < 5; i++) {
                Individual person = generator.generateAndSave();
                System.out.println("Сгенерирован: "
                        + person.getLastName() + " "
                        + person.getFirstName());
            }
        };
    }
}
package org.cbr.generator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class DateGenerator {
    private final Random random = new Random();

    public LocalDate generateBirthDate(int minAge, int maxAge) {
        int currentYear = LocalDate.now().getYear();
        int randomAge = minAge + random.nextInt(maxAge - minAge + 1);
        int birthYear = currentYear - randomAge;
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28); // Упрощаем, не учитываем февраль

        return LocalDate.of(birthYear, month, day);
    }
}
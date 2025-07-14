package org.cbr.generator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InnGenerator {
    private final Random random = new Random();

    public String generateForIndividual() {
        // ИНН физлица - 12 цифр
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(random.nextInt(10));
        }

        String base = sb.toString();
        int control11 = calculateControlDigit(base, new int[]{7, 2, 4, 10, 3, 5, 9, 4, 6, 8});
        int control12 = calculateControlDigit(base + control11, new int[]{3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8});

        return base + control11 + control12;
    }

    private int calculateControlDigit(String digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            sum += digit * weights[i];
        }
        return (sum % 11) % 10;
    }
}


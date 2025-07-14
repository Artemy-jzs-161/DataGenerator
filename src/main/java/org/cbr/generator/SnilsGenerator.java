package org.cbr.generator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SnilsGenerator {
    private final Random random = new Random();

    public String generate() {
        // Генерируем 9 случайных цифр
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            sb.append(random.nextInt(10));
        }

        String base = sb.toString();
        int controlSum = calculateControlSum(base);

        // Форматируем как СНИЛС: XXX-XXX-XXX YY
        return String.format("%s-%s-%s %02d",
                base.substring(0, 3),
                base.substring(3, 6),
                base.substring(6, 9),
                controlSum);
    }

    private int calculateControlSum(String digits) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(digits.charAt(i));
            sum += digit * (9 - i);
        }

        if (sum < 100) return sum;
        if (sum == 100 || sum == 101) return 0;
        return sum % 101;
    }
}

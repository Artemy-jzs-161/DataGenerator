package ru.temaborovik.generate.person;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InnGenerator {

    private final Random random = new Random();

    private static final int INDIVIDUAL_LENGTH = 12;
    private static final int LEGAL_ENTITY_LENGTH = 10;

    private static final int[] CONTROL_WEIGHTS_11 = {7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    private static final int[] CONTROL_WEIGHTS_12 = {3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8};
    private static final int[] CONTROL_WEIGHTS_10 = {2, 4, 10, 3, 5, 9, 4, 6, 8};

    /**
     * Генерация ИНН для физ лица
     */
    public String generateForIndividual() {
        int[] digits = new int[INDIVIDUAL_LENGTH];
        for (int i = 0; i < 10; i++) {
            digits[i] = random.nextInt(10);
        }

        digits[10] = calculateControlDigit(digits, CONTROL_WEIGHTS_11);
        digits[11] = calculateControlDigit(digits, CONTROL_WEIGHTS_12);

        return arrayToString(digits);
    }

    /**
     * Генерация ИНН для юр лица (без региона)
     */
    public String generateForLegalEntity() {
        int[] digits = new int[LEGAL_ENTITY_LENGTH];
        for (int i = 0; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }

        digits[9] = calculateControlDigit(digits, CONTROL_WEIGHTS_10);

        return arrayToString(digits);
    }

    /**
     * Генерация ИНН для юр лица по заданному региону (две цифры)
     */
    public String generateWithRegion(String regionCode) {
        if (regionCode == null || !regionCode.matches("\\d{2}")) {
            throw new IllegalArgumentException("Некорректный код региона: " + regionCode);
        }

        int[] digits = new int[LEGAL_ENTITY_LENGTH];

        // Первые 2 цифры — регион
        digits[0] = Character.getNumericValue(regionCode.charAt(0));
        digits[1] = Character.getNumericValue(regionCode.charAt(1));

        // Следующие 7 — случайные
        for (int i = 2; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }

        // Контрольная цифра
        digits[9] = calculateControlDigit(digits, CONTROL_WEIGHTS_10);

        return arrayToString(digits);
    }

    private int calculateControlDigit(int[] digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += digits[i] * weights[i];
        }
        return (sum % 11) % 10;
    }

    private String arrayToString(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }
}

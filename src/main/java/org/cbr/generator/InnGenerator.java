package org.cbr.generator;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class InnGenerator {
    private static final Random random = new Random();

    public String generateForIndividual() {
        //Генерация первых 12 цифр
        int[] digits = new int[12];
        for (int i = 0; i < 10; i++) {
            digits[i] = random.nextInt(10);
        }

        // Вычисление 11-ой контрольной цифры
        digits[10] = calculateControlDigit(
                digits,
                11,
                new int[]{7, 2, 4, 10, 3, 5, 9, 4, 6, 8});

        digits[11] = calculateControlDigit(
                digits,
                12,
                new int[]{3, 7, 2, 4, 10, 3, 5, 9, 4, 6, 8}
        );

        return arrayToString(digits);

    }

    /**
     * Генерация ИНН для юридического лица (10 цифр)
     */
    public static String generateEntryInn() {
        //Генерация первых 9 цифр
        int[] digits = new int[10];
        for (int i = 0; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }
        digits[9] = calculateControlDigit(
                digits,
                10,
                new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8}
        );

        return arrayToString(digits);
    }

    /**
     * @param digits  массив цифр ИНН
     * @param length  текущяя длина ИНН
     * @param weights весовые коэффициенты
     * @return контрольная цифр
     */

    private static int calculateControlDigit(int[] digits, int length, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += digits[i] * weights[i];
        }
        return (sum % 11) % 10;
    }

    /**
     * Преобразование массива цифр в строку
     */
    private static String arrayToString(int[] digits) {
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            sb.append(digit);
        }
        return sb.toString();
    }
}
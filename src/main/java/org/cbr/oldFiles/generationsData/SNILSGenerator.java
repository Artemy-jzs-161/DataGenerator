package org.cbr.oldFiles.generationsData;

import java.util.Random;

public class SNILSGenerator {
    private static final Random random = new Random();

    public static String generateSnils() {
        // Генерация первыех 9 цифр (случайные)
        int[] digits = new int[9];
        for (int i = 0; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }

        // Вычисление контрольной суммы
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (9 - i);
        }





        // Вычисляем контрольное число
        int controlNumber;
        if (sum < 100) {
            controlNumber = sum;
        } else if (sum == 100 || sum == 101) {
            controlNumber = 0;
        } else {
            controlNumber = sum % 101;
            if (controlNumber == 100) {
                controlNumber = 0;
            }
        }

        // Формат СНИЛСа в стандартный вид
        return String.format("%03d-%03d-%03d %02d",
                digits[0] * 100 + digits[1] * 10 + digits[2],
                digits[3] * 100 + digits[4] * 10 + digits[5],
                digits[6] * 100 + digits[7] * 10 + digits[8],
                controlNumber);
    }
}

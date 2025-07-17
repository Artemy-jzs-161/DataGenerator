package org.cbr.generator.ogrn;

import org.cbr.enums.OgrnType;
import org.cbr.enums.RegistrationReason;
import org.cbr.enums.TaxRegion;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.cbr.generator.ogrn.CalculateControlDigit.calculateControlDigit;
import static org.cbr.generator.ogrn.GenerateRecordNumber.generateRecordNumber;

@Component
public class OgrnGenerator {
    public static final Random RANDOM = ThreadLocalRandom.current();

    // Запрет инстанса
    public OgrnGenerator() {
    }

    private static String generate(OgrnType type,
                                  TaxRegion region,
                                  RegistrationReason reason) {
        StringBuilder builder = new StringBuilder(type.getLength());

        // Первая цифра (1 или 5 для ЮЛ, или 3 для ИП)
        builder.append(type == OgrnType.LEGAL_ENTITY ? (RANDOM.nextBoolean() ? '1' : '5') : '3');

        // Год регистрации (2 цифры) для новых регистраций последние 5 лет
        int currentYear = LocalDate.now().getYear() % 100;
        int year = currentYear - RANDOM.nextInt(5);
        if (year < 0) year += 100;
        builder.append(String.format("%02d", year));

        // Код субъекта РФ (2 цифры)
        builder.append(region.getCode());

        // Номер записи
        builder.append(generateRecordNumber(type, reason, region));

        // Контрольная сумма
        builder.append(calculateControlDigit(builder.toString()));

        return builder.toString();
    }

    public String generateOrganizationOgrn() {
        return generate(
                OgrnType.LEGAL_ENTITY,
                TaxRegion.getRandomWeighted(),
                RegistrationReason.REASON_01);
    }

    public String generateIpOgrn() {
        return generate(
                OgrnType.INDIVIDUAL_ENTREPRENEUR,
                TaxRegion.getRandomWeighted(),
                RegistrationReason.REASON_05);
    }

    //Проверка валидности ОГРН
    public static boolean isValid(String ogrn) {
        if (!isValidFormat(ogrn)) {
            return false;
        }

        try {
            String base = ogrn.substring(0, ogrn.length() - 1);
            char actualControlDigit = ogrn.charAt(ogrn.length() - 1);
            char expectedControlDigit = calculateControlDigit(base);
            return actualControlDigit == expectedControlDigit;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidFormat(String ogrn) {
        return ogrn != null && (ogrn.matches("\\d{13}") || ogrn.matches("\\d{15}"));
    }

    public static OgrnType detectType(String orgn) {
        if (!isValid(orgn)) {
            throw new IllegalArgumentException("Invalid orgn");
        }
        return orgn.length() == 13 ?
                OgrnType.LEGAL_ENTITY :
                OgrnType.INDIVIDUAL_ENTREPRENEUR;
    }
}

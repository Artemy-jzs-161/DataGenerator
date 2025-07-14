package org.cbr.oldFiles.generationsData;

import org.cbr.enums.RegistrationReason;
import org.cbr.enums.TaxRegion;

import java.util.Random;

public class KPPGenerator {
    private static final Random RANDOM = new Random();

    /**
     * Генерация КПП
     */
    public static String generate() {
        // Регион
        TaxRegion region = getRandomTaxRegion();
        // Код ИФНС
        String taxOffice = String.format("%02d", RANDOM.nextInt(100));

        // Причина постановки
        RegistrationReason reason = getRandomReason();

        // Порядковый номер (001-999)
        String serialNumber = String.format("%03d", RANDOM.nextInt(999) + 1);

        return region.getCode() +
                taxOffice
                + reason.getCode()
                + serialNumber;
    }

    /**
     * Проверка валидности КПП
     */
    public static boolean isValid(String kpp) {
        if (kpp == null || kpp.length() != 9 || !kpp.matches("\\d{9}")) {
            return false;
        }

        String regionCode = kpp.substring(0, 2);
        String reasonCode = kpp.substring(4, 6);

        return TaxRegion.getByCode(regionCode) != null &&
                RegistrationReason.getByCode(reasonCode) != null;
    }

    private static TaxRegion getRandomTaxRegion() {
        TaxRegion[] regions = TaxRegion.values();
        return regions[RANDOM.nextInt(regions.length)];
    }

    private static RegistrationReason getRandomReason() {
        RegistrationReason[] reasons = RegistrationReason.values();
        return reasons[RANDOM.nextInt(reasons.length)];
    }

    /**
     * Получение описания КПП
     */
    public static String getDescription(String kpp) {
        if (!isValid(kpp)) {
            return "Неверный КПП";
        }

        TaxRegion region = TaxRegion.getByCode(kpp.substring(0, 2));
        String taxOffice = kpp.substring(2, 4);
        RegistrationReason reason = RegistrationReason.getByCode(kpp.substring(4, 6));
        String serialNumber = kpp.substring(6);

        return String.format(
                "КПП: %s\n- Регион: %s (%s)\n- ИФНС: %s\n- Причина: %s\n- Номер: %s",
                kpp,
                region.getCode(),
                region.getName(),
                taxOffice,
                reason.getDescription(),
                serialNumber
        );
    }
}

package org.cbr.generator;

import org.cbr.enums.RegistrationReason;
import org.cbr.enums.TaxRegion;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class KppGenerator {
    private static final Random RANDOM = new Random();

    public String generate() {
        //region
        TaxRegion region = getRandomTaxRegion();
        //код ИФНС
        String taxOffice = String.format("%02d", RANDOM.nextInt(100));
        //причина постановки
        RegistrationReason reason = getRandomTaxReason();
        //порядковый номер (001-999)
        String serialNumber = String.format("%04d", RANDOM.nextInt(999) + 1);

        return region.getCode()
                + taxOffice
                + reason.getCode()
                + serialNumber;
    }

    //получение описание КПП
    public static String getDescription(String kpp) {
        if (!isValid(kpp)) {
            return "Неверный КПП";
        }

        TaxRegion region = TaxRegion.getByCode(kpp.substring(0, 2));
        String taxOffice = kpp.substring(2, 4);
        RegistrationReason reason = RegistrationReason.getByCode(kpp.substring(4, 6));
        String serialNumber = kpp.substring(6);

        assert region != null;
        assert reason != null;
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

    private TaxRegion getRandomTaxRegion() {
        TaxRegion[] regions = TaxRegion.values();
        return regions[RANDOM.nextInt(regions.length)];
    }

    private RegistrationReason getRandomTaxReason() {
        RegistrationReason[] reasons = RegistrationReason.values();
        return reasons[RANDOM.nextInt(reasons.length)];
    }

    //Проверка валидности КПП
    public static boolean isValid(String kpp) {
        if (kpp == null || kpp.length() != 9 || !kpp.matches("\\d{9}")) {
            return false;
        }

        String regionCode = kpp.substring(0, 2);
        String reasonCode = kpp.substring(4, 6);

        return TaxRegion.getByCode(regionCode) != null &&
                RegistrationReason.getByCode(reasonCode) != null;
    }
}

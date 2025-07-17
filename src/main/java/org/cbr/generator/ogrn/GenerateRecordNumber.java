package org.cbr.generator.ogrn;

import org.cbr.enums.OgrnType;
import org.cbr.enums.RegistrationReason;
import org.cbr.enums.TaxRegion;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.cbr.generator.ogrn.OgrnGenerator.*;

public class GenerateRecordNumber {
    public static String generateRecordNumber(OgrnType type,
                                              RegistrationReason reason,
                                              TaxRegion region) {
        int baseNumber = switch (reason) {
            case REASON_31, REASON_32 -> 300_000 + RANDOM.nextInt(200_000);
            case REASON_51 -> 500_000 + RANDOM.nextInt(200_000);
            default -> {
                int regionFactor = Integer.parseInt(region.getCode());
                yield Math.abs(RANDOM.nextInt(300_000) + regionFactor);
            }
        };

        int length = type == OgrnType.LEGAL_ENTITY ? 6 : 9;
        if (type == OgrnType.INDIVIDUAL_ENTREPRENEUR) {
            baseNumber *= 1000;
        }
        return String.format("%0" + length + "d", baseNumber);
    }

    static Map<String, Double> createRegionWeights() {
        return Arrays.stream(TaxRegion.values())
                .collect(Collectors
                        .toMap(TaxRegion::getCode,
                                TaxRegion::getWeight));
    }
}

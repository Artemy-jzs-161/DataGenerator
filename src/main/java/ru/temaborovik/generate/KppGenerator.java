package ru.temaborovik.generate;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.temaborovik.model.Region;
import ru.temaborovik.model.RegistrationReason;
import ru.temaborovik.repository.RegionRepository;
import ru.temaborovik.repository.RegistrationReasonRepository;

import java.util.Random;

@Component
@RequiredArgsConstructor
public class KppGenerator {
    private static final Random RANDOM = new Random();
    private final RegionRepository regionRepository;
    private final RegistrationReasonRepository reasonRepository;

    // Генерация КПП по указанному коду региона
    private String generate(String regionCode) {
        Region region = regionRepository.findById(regionCode)
                .orElseThrow(() -> new IllegalArgumentException("Регион с кодом " + regionCode + " не найден"));

        RegistrationReason reason = reasonRepository.findRandomReason();

        String taxOffice = String.format("%02d", RANDOM.nextInt(100));
        String serialNumber = String.format("%04d", RANDOM.nextInt(999) + 1);

        return region.getCode() + taxOffice + reason.getCode() + serialNumber;
    }

    // Генерация случайного КПП
    private String generate() {
        Region region = regionRepository.findRandomRegion();
        return generate(region.getCode());
    }

    // Получение описания по КПП
    public String getDescription(String kpp) {
        if (!isValid(kpp)) return "Не верный КПП";

        String regionCode = kpp.substring(0, 2);
        String taxOffice = kpp.substring(2, 4);
        String reasonCode = kpp.substring(4, 6);
        String serialNumber = kpp.substring(6);

        Region region = regionRepository.findById(regionCode).orElse(null);
        RegistrationReason reason = reasonRepository.findById(reasonCode).orElse(null);

        if (region == null || reason == null) {
            return "Регион или причина не найдены в БД";
        }
        return String.format(
                "КПП: %s\n- Регион: %s (%s)\n- ИФНС: %s\n- Причина: %s\n- Номер: %s",
                kpp,
                region.getCode(),
                region.getName(),
                taxOffice,
                reason.getDescription(),
                serialNumber);
    }

    public boolean isValid(String kpp) {
        if (kpp == null || !kpp.matches("\\d{9}")) return false;

        String regionCode = kpp.substring(0, 2);
        String reasonCode = kpp.substring(4, 6);

        return regionRepository.existsById(regionCode)
                && reasonRepository.existsById(reasonCode);
    }

}

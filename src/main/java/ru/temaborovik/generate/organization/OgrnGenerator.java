package ru.temaborovik.generate.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.temaborovik.enums.OgrnType;
import ru.temaborovik.entity.Region;
import ru.temaborovik.repository.RegionRepository;

import java.time.LocalDate;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class OgrnGenerator {
    private final RegionRepository regionRepository;
    private static final Random RANDOM = new Random();

    public String generateOrganizationOgrn() {
        Region region = regionRepository.findRandomRegion();
        return generate(OgrnType.LEGAL_ENTITY, region);
    }

    public String generateOrganizationOgrn(String regionCode) {
        Region region = regionRepository.findById(regionCode)
                .orElseThrow(() -> new IllegalArgumentException("Регион не найден: " + regionCode));
        return generate(OgrnType.LEGAL_ENTITY, region);
    }

    public String generateIpOgrn() {
        Region region = regionRepository.findRandomRegion();
        return generate(OgrnType.INDIVIDUAL_ENTREPRENEUR, region);
    }

    public String generateIpOgrn(String regionCode) {
        Region region = regionRepository.findById(regionCode)
                .orElseThrow(() -> new IllegalArgumentException("Регион не найден: " + regionCode));
        return generate(OgrnType.INDIVIDUAL_ENTREPRENEUR, region);
    }

    public String generate(OgrnType type, Region region) {
        StringBuilder sb = new StringBuilder();

        // Тип: 1 или 3
        sb.append(type == OgrnType.LEGAL_ENTITY ? '1' : '3');

        // Год регистрации
        int year = LocalDate.now().getYear() % 100 - RANDOM.nextInt(5);
        if (year < 0) year += 100;
        sb.append(String.format("%02d", year));

        // Регион (2 цифры)
        sb.append(region.getCode());


        // Номер записи: 6 или 9 цифр
        int numberLength = type == OgrnType.LEGAL_ENTITY ? 6 : 9;
        long number = Math.abs(RANDOM.nextLong()) % (long) Math.pow(10, numberLength);
        sb.append(String.format("%0" + numberLength + "d", number));

        // Контрольная цифра
        sb.append(calculateControlDigit(sb.toString()));
        return sb.toString();
    }

    public boolean isValid(String ogrn) {
        if (ogrn == null || (!ogrn.matches("\\d{13}") && !ogrn.matches("\\d{15}"))) {
            return false;
        }
        String base = ogrn.substring(0, ogrn.length() - 1);
        char actual = ogrn.charAt(ogrn.length() - 1);
        return actual == calculateControlDigit(base);
    }

    private char calculateControlDigit(String base) {
        long num = Long.parseLong(base);
        int mod = base.length() == 12 ? 11 : 13;
        int control = (int) (num % mod) % 10;
        return (char) ('0' + control);
    }
}

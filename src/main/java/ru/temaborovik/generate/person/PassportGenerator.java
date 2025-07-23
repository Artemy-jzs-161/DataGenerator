package ru.temaborovik.generate.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.temaborovik.entity.Region;
import ru.temaborovik.repository.RegionRepository;

import java.util.Optional;
import java.util.Random;


@Component
@RequiredArgsConstructor
public class PassportGenerator {
    private final RegionRepository regionRepository;
    private static final Random random = new Random();

    /**
     * Генерация паспорта с рандомным регионом из базы
     */
    public String generateRandom() {
        Region region = regionRepository.findRandomRegion();
        return generateFromRegionCode(region.getCode());
    }

    /**
     * Генерация паспорта по заданному региону (например "45", "77")
     */
    public String generateFromRegionCode(String regionCode) {
        Optional<Region> optionalRegion = regionRepository.findById(regionCode);
        if (optionalRegion.isEmpty()) {
            throw new IllegalArgumentException("Регион с кодом " + regionCode + " не найден.");
        }

        int suffix = random.nextInt(100); // две цифры после региона
        String series = regionCode + String.format("%02d", suffix);

        int number = random.nextInt(1_000_000); // номер паспорта

        return String.format("%s %06d", series, number);
    }
}
package org.cbr.oldfiles.enums;

import java.util.Arrays;

public enum RussianRegion {
    ADYGEA("01", "Республика Адыгея"),
    BASHKORTOSTAN("02", "Республика Башкортостан"),
    BURYATIA("03", "Республика Бурятия"),
    ALTAI("04", "Республика Алтай"),
    DAGESTAN("05", "Республика Дагестан"),
    INGUSHETIA("06", "Республика Ингушетия"),
    KABARDINO_BALKARIA("07", "Кабардино-Балкарская Республика"),
    KALMYKIA("08", "Республика Калмыкия"),
    KARACHAY_CHERKESSIA("09", "Карачаево-Черкесская Республика"),
    KARELIA("10", "Республика Карелия"),
    KOMI("11", "Республика Коми"),
    MARI_EL("12", "Республика Марий Эл"),
    MORDOVIA("13", "Республика Мордовия"),
    SAKHA("14", "Республика Саха (Якутия)"),
    NORTH_OSSETIA("15", "Республика Северная Осетия - Алания"),
    TATARSTAN("16", "Республика Татарстан"),
    TYVA("17", "Республика Тыва"),
    UDMURTIA("18", "Удмуртская Республика"),
    KHAKASIA("19", "Республика Хакасия"),
    CHELYABINSK("20", "Челябинская область"),
    // ... остальные регионы
    MOSCOW("77", "Москва"),
    SAINT_PETERSBURG("78", "Санкт-Петербург"),
    JEWISH_AO("79", "Еврейская автономная область"),
    NENETS_AO("83", "Ненецкий автономный округ"),
    // ... другие автономные округа
    BAIKONUR("99", "Байконур");

    private final String code;
    private final String name;
    private final double weight; // Вес для вероятностного распределения

    RussianRegion(String code, String name) {
        this(code, name, 1.0);
    }

    RussianRegion(String code, String name, double weight) {
        this.code = code;
        this.name = name;
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public static RussianRegion getByCode(String code) {
        for (RussianRegion region : values()) {
            if (region.code.equals(code)) {
                return region;
            }
        }
        return null;
    }

    // Метод для получения регионов с учетом весов
    public static RussianRegion getRandomWeighted() {
        double totalWeight = Arrays.stream(values())
                .mapToDouble(RussianRegion::getWeight)
                .sum();

        double random = Math.random() * totalWeight;
        double cumulativeWeight = 0.0;

        for (RussianRegion region : values()) {
            cumulativeWeight += region.getWeight();
            if (random <= cumulativeWeight) {
                return region;
            }
        }
        return MOSCOW; // fallback
    }
}


package org.cbr.enums;

import java.util.Arrays;

public enum TaxRegion {
    REGION_01("01", "Республика Адыгея (Адыгея)"),
    REGION_02("02", "Республика Башкортостан"),
    REGION_03("03", "Республика Бурятия"),
    REGION_04("04", "Республика Алтай"),
    REGION_05("05", "Республика Дагестан"),
    REGION_06("06", "Республика Ингушетия"),
    REGION_07("07", "Кабардино-Балкарская Республика"),
    REGION_08("08", "Республика Калмыкия"),
    REGION_09("09", "Карачаево-Черкесская Республика"),
    REGION_10("10", "Республика Карелия"),
    REGION_11("11", "Республика Коми"),
    REGION_12("12", "Республика Марий Эл"),
    REGION_13("13", "Республика Мордовия"),
    REGION_14("14", "Республика Саха (Якутия)"),
    REGION_15("15", "Республика Северная Осетия - Алания"),
    REGION_16("16", "Республика Татарстан (Татарстан)"),
    REGION_17("17", "Республика Тыва"),
    REGION_18("18", "Удмуртская Республика"),
    REGION_19("19", "Республика Хакасия"),
    REGION_20("20", "Чеченская Республика"),
    REGION_21("21", "Чувашская Республика - Чувашия"),
    REGION_22("22", "Алтайский край"),
    REGION_23("23", "Краснодарский край"),
    REGION_24("24", "Красноярский край"),
    REGION_25("25", "Приморский край"),
    REGION_26("26", "Ставропольский край"),
    REGION_27("27", "Хабаровский край"),
    REGION_28("28", "Амурская область"),
    REGION_29("29", "Архангельская область"),
    REGION_30("30", "Астраханская область"),
    REGION_31("31", "Белгородская область"),
    REGION_32("32", "Брянская область"),
    REGION_33("33", "Владимирская область"),
    REGION_34("34", "Волгоградская область"),
    REGION_35("35", "Вологодская область"),
    REGION_36("36", "Воронежская область"),
    REGION_37("37", "Ивановская область"),
    REGION_38("38", "Иркутская область"),
    REGION_39("39", "Калининградская область"),
    REGION_40("40", "Калужская область"),
    REGION_41("41", "Камчатский край"),
    REGION_42("42", "Кемеровская область"),
    REGION_43("43", "Кировская область"),
    REGION_44("44", "Костромская область"),
    REGION_45("45", "Курганская область"),
    REGION_46("46", "Курская область"),
    REGION_47("47", "Ленинградская область"),
    REGION_48("48", "Липецкая область"),
    REGION_49("49", "Магаданская область"),
    REGION_50("50", "Московская область"),
    REGION_51("51", "Мурманская область"),
    REGION_52("52", "Нижегородская область"),
    REGION_53("53", "Новгородская область"),
    REGION_54("54", "Новосибирская область"),
    REGION_55("55", "Омская область"),
    REGION_56("56", "Оренбургская область"),
    REGION_57("57", "Орловская область"),
    REGION_58("58", "Пензенская область"),
    REGION_59("59", "Пермский край"),
    REGION_60("60", "Псковская область"),
    REGION_61("61", "Ростовская область"),
    REGION_62("62", "Рязанская область"),
    REGION_63("63", "Самарская область"),
    REGION_64("64", "Саратовская область"),
    REGION_65("65", "Сахалинская область"),
    REGION_66("66", "Свердловская область"),
    REGION_67("67", "Смоленская область"),
    REGION_68("68", "Тамбовская область"),
    REGION_69("69", "Тверская область"),
    REGION_70("70", "Томская область"),
    REGION_71("71", "Тульская область"),
    REGION_72("72", "Тюменская область"),
    REGION_73("73", "Ульяновская область"),
    REGION_74("74", "Челябинская область"),
    REGION_75("75", "Забайкальский край"),
    REGION_76("76", "Ярославская область"),
    REGION_77("77", "г. Москва"),
    REGION_78("78", "Санкт-Петербург"),
    REGION_79("79", "Еврейская автономная область"),
    REGION_83("83", "Ненецкий автономный округ"),
    REGION_86("86", "Ханты-Мансийский автономный округ - Югра"),
    REGION_87("87", "Чукотский автономный округ"),
    REGION_89("89", "Ямало-Ненецкий автономный округ"),
    REGION_99("99", "Иные территории, включая город и космодром Байконур");

    private final String code;
    private final String name;
    private final double weight; // Вес для вероятностного распределения


    TaxRegion(String code, String name, double weight) {
        this.code = code;
        this.name = name;
        this.weight = weight;
    }

    TaxRegion(String name, String code) {
        this.name = name;
        this.code = code;
        this.weight = 1.0;
    }

    public double getWeight() {
        return weight;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static TaxRegion getByCode(String code) {
        for(TaxRegion region : values()) {
            if (region.code.equals(code)) {
                return region;
            }
        } return null;
    }

    // Метод для получения регионов с учетом весов
    public static TaxRegion getRandomWeighted() {
        double totalWeight = Arrays.stream(values())
                .mapToDouble(TaxRegion::getWeight)
                .sum();

        double random = Math.random() * totalWeight;
        double cumulativeWeight = 0.0;

        for (TaxRegion region : values()) {
            cumulativeWeight += region.getWeight();
            if (random <= cumulativeWeight) {
                return region;
            }
        }
        return REGION_77; // fallback
    }
}

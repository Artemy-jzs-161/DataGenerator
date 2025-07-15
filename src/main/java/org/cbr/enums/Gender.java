package org.cbr.enums;

public enum Gender {
    MALE ("Мужчина"),
    FEMALE ("Женщина");

    private String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }
}

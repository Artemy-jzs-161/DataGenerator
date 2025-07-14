package org.cbr.oldFiles.enums;

public enum OgrnType {
    LEGAL_ENTITY(13, "ОГРН юридического лица"),
    INDIVIDUAL_ENTREPRENEUR(15,"ОГРНИП индивидуального предпринимателя");

    private final int length;
    private final String description;

    OgrnType(int length, String description) {
        this.length = length;
        this.description = description;
    }

    public int getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }
}
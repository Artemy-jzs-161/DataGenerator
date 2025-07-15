package org.cbr.enums;

public enum BusinessForms {
    OOO("Общество с ограниченной ответственностью"),
    AO("Акционерное общество"),
    IP("Индивидуальный предприниматель"),
    TOO("Товарищество с ограниченной ответственностью"),
    PK("Производственный кооператив"),
    KFH("Крестьянское (фермерское) хозяйство"),
    GUPE("Государственное или муниципальное унитарное предприятие"),
    PH("Полное товарищество"),
    TN("Товарищество на вере (коммандитное товарищество)"),
    HP("Хозяйственное партнёрство");

    private String formName;

    BusinessForms(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }
}

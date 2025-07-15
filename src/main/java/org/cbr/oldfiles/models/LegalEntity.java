package org.cbr.oldfiles.models;

import java.time.LocalDate;
import java.util.List;

public class LegalEntity extends Subject {
    private String name;
    private String shortName;
    private String ogrn;
    private String inn;
    private String kpp;
    private LocalDate registrationDate;
    private List<Individual> founders;
    private List<Okved> okveds;
    // ... другие реквизиты
}

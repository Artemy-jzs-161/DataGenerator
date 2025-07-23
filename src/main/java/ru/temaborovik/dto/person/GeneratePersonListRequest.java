package ru.temaborovik.dto.person;

import lombok.Data;

@Data
public class GeneratePersonListRequest {
    private int count = 1;          // сколько генерировать, по умолчанию 1
    private boolean save = true;    // сохранять ли в БД, по умолчанию true
}

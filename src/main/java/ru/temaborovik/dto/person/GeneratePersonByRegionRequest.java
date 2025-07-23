package ru.temaborovik.dto.person;

import lombok.Data;

@Data
public class GeneratePersonByRegionRequest {
    private String regionCode;      // код региона, обязателен
    private int count = 1;
    private boolean save = true;
}
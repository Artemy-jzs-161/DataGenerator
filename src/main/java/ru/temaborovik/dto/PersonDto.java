package ru.temaborovik.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String snils;
    private String inn;
    private String passport;
}
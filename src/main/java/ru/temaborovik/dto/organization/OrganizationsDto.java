package ru.temaborovik.dto.organization;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrganizationsDto {
    private String name;
    private String ogrn;
    private String kpp;
}



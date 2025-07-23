package ru.temaborovik.dto.organization;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.temaborovik.enums.BusinessForms;

@Data
public class OrganizationsRequest {
    private String regionCode;

    @NotNull
    private BusinessForms businessForm;

    @Min(1) @Max(100)
    private int count = 1; // сколько генерировать, по умолчанию 1

    private boolean save = true;    // сохранять ли в БД, по умолчанию true
}

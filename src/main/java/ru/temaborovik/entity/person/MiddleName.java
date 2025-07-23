package ru.temaborovik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "middle_names")
public class MiddleName {
    @Id
    @GeneratedValue
    private Long id;
    private String middleName;
}

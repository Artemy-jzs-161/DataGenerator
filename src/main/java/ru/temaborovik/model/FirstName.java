package ru.temaborovik.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "first_names")
public class FirstName {
@Id
@GeneratedValue
    private Long id;
    private String firstName;
}

package ru.temaborovik.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "regions")
public class Region {

    @Id
    private String code;

    private String name;
}
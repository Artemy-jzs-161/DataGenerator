package org.cbr.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "middle_names")
public class MiddleName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private String culture;
}
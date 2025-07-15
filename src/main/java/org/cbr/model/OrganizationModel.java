package org.cbr.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "organizations")
public class OrganizationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String inn;

    @Column(unique = true)
    private String kpp;

    @Column(unique = true)
    private String ogrn;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt = LocalDate.now();
}

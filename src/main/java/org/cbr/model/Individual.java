package org.cbr.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "individuals")
public class Individual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String middleName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(unique = true)
    private String snils;

    @Column(unique = true)
    private String inn;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt = LocalDate.now();}

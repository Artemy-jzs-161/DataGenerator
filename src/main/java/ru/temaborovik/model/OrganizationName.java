package ru.temaborovik.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "organization_names")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
}
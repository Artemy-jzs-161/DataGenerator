package ru.temaborovik.entity.organization;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stems_names")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StemsName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
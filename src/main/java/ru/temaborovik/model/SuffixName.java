package ru.temaborovik.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suffix_names")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuffixName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
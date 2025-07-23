package ru.temaborovik.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "regions")
public class Region {
    @Id
    private String code;

    @Column(nullable = false)
    private String name;
}

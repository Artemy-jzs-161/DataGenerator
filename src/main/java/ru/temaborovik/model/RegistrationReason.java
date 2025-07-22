package ru.temaborovik.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "registration_reason")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationReason {
    @Id
    private String code;

    @Column(nullable = false)
    private String description;
}
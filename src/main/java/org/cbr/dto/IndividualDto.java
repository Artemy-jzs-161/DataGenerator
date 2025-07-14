package org.cbr.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cbr.model.Individual;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndividualDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String snils;
    private String inn;
}

package org.cbr.model.fio;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "first_names")
public class FirstName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private String culture;
    private Integer frequency;

}
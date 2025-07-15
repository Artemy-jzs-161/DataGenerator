package org.cbr.model.companyname;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "stemsname")
public class StemsName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}



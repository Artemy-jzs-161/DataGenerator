package org.cbr.model.companyname;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "suffixname")
public class SuffixName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

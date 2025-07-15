package org.cbr.model.companyname;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "prefixname")
public class PrefixName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}


package org.cbr.model.fio;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "last_names")
@Entity
public class LastName {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String gender;
        private String culture;
}

package org.cbr.repository;

import org.cbr.model.IndividualModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<IndividualModel, Long> {
    boolean existsBySnils(String snils);
    boolean existsByInn(String inn);
}

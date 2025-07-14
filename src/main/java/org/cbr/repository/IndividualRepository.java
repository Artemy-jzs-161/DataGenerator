package org.cbr.repository;

import org.cbr.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
    boolean existsBySnils(String snils);
    boolean existsByInn(String inn);
}

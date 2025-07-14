package org.cbr.repository;

import org.cbr.model.MiddleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MiddleNameRepository extends JpaRepository<MiddleName, Long> {

    @Query("SELECT m.name FROM MiddleName m ORDER BY RANDOM() LIMIT 1")
    String findRandomMiddleName();
}

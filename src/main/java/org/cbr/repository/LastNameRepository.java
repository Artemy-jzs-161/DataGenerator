package org.cbr.repository;

import org.cbr.model.LastName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LastNameRepository extends JpaRepository<LastName, Long> {

    @Query("SELECT l.name FROM LastName l ORDER BY RANDOM() LIMIT 1")
    String findRandomLastName();
}


package org.cbr.repository.fio;


import org.cbr.model.fio.FirstName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FirstNameRepository extends JpaRepository<FirstName, Long> {

    @Query("SELECT f.name FROM FirstName f ORDER BY RANDOM() LIMIT 1")
    String findRandomFirstName();
}
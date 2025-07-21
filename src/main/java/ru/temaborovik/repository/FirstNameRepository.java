package ru.temaborovik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.temaborovik.model.FirstName;

public interface FirstNameRepository extends JpaRepository<FirstName, Long> {

    @Query(value = "SELECT * FROM first_names ORDER BY random() LIMIT 1", nativeQuery = true)
    FirstName findRandomFirstName();
}
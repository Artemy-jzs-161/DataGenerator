package ru.temaborovik.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.temaborovik.model.MiddleName;

public interface MiddleNameRepository extends JpaRepository<MiddleName, Long> {

    @Query(value = "SELECT * FROM middle_names ORDER BY random() LIMIT 1", nativeQuery = true)
    MiddleName findRandomMiddleName();
}
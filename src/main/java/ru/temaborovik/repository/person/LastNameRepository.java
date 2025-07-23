package ru.temaborovik.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.temaborovik.model.LastName;

public interface LastNameRepository extends JpaRepository<LastName, Long> {

    @Query(value = "SELECT * FROM last_names ORDER BY random() LIMIT 1", nativeQuery = true)
    LastName findRandomLastName();
}
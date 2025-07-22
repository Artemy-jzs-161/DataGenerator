package ru.temaborovik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.temaborovik.model.PrefixName;

@Repository
public interface PrefixNameRepository extends JpaRepository<PrefixName, Long> {
    @Query(value = "SELECT * FROM prefix_names ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    PrefixName findRandomPrefixName();
}
package ru.temaborovik.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.temaborovik.entity.organization.SuffixName;

@Repository
public interface SuffixNameRepository extends JpaRepository<SuffixName, Long> {
    @Query(value = "SELECT * FROM suffix_names ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    SuffixName findRandomSuffixName();
}
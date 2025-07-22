package ru.temaborovik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.temaborovik.model.OrganizationName;

@Repository
public interface OrganizationNameRepository extends JpaRepository<OrganizationName, Long> {
    boolean existsByName(String name);
}
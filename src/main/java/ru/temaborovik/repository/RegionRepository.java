package ru.temaborovik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.temaborovik.model.Region;

public interface RegionRepository extends JpaRepository<Region, String> {

    @Query(value = "SELECT * FROM regions ORDER BY random() LIMIT 1", nativeQuery = true)
    Region findRandomRegion();


}
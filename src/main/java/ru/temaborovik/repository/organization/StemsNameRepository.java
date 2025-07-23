package ru.temaborovik.repository.organization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.temaborovik.entity.organization.StemsName;

@Repository
public interface StemsNameRepository extends JpaRepository<StemsName, Long> {
    @Query(value = "select * from stems_names order by random() limit 1", nativeQuery = true)
    StemsName findRandomStemsName();
}

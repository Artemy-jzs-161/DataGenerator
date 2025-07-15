package org.cbr.repository.companyname;

import org.cbr.model.companyname.StemsName;
import org.cbr.model.fio.FirstName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StemsNameRepository extends JpaRepository<StemsName, Long> {

    @Query("SELECT s.name FROM StemsName s ORDER BY RANDOM() LIMIT 1")
    String findRandomStepsName();
}

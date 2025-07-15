package org.cbr.repository.companyname;

import org.cbr.model.companyname.PrefixName;
import org.cbr.model.companyname.StemsName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrefixNameRepository extends JpaRepository<PrefixName, Long> {

    @Query("SELECT p.name FROM PrefixName p ORDER BY RANDOM() LIMIT 1")
    String findRandomPrefixName();
}
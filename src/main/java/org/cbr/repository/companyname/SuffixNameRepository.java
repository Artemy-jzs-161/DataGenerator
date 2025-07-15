package org.cbr.repository.companyname;

import org.cbr.model.companyname.SuffixName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SuffixNameRepository extends JpaRepository<SuffixName, Long> {

    @Query("SELECT s.name FROM SuffixName s ORDER BY RANDOM() LIMIT 1")
    String findRandomSuffixName();
}

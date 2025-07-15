package org.cbr.repository;

import org.cbr.model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<OrganizationModel, Long> {
    boolean existsByInn(String inn);
    boolean existsByKpp(String kpp);
    boolean existsByOgrn(String ogrn);
}

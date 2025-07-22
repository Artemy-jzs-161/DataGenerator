package ru.temaborovik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.temaborovik.model.RegistrationReason;

public interface RegistrationReasonRepository extends JpaRepository<RegistrationReason, String> {
    @Query(value = "SELECT * FROM registration_reason ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    RegistrationReason findRandomReason();
}

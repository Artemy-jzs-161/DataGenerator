package ru.temaborovik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.temaborovik.model.PersonEntity;

public interface PersonRepository extends JpaRepository <PersonEntity, Long> {
}

package ru.temaborovik.repository.person;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.temaborovik.entity.person.PersonEntity;

public interface PersonRepository extends JpaRepository <PersonEntity, Long> {
}

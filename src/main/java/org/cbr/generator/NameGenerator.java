package org.cbr.generator;

import org.cbr.repository.FirstNameRepository;
import org.cbr.repository.LastNameRepository;
import org.cbr.repository.MiddleNameRepository;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NameGenerator {
    private final FirstNameRepository firstNameRepository;
    private final MiddleNameRepository middleNameRepository;
    private final LastNameRepository lastNameRepository;

    public NameGenerator(FirstNameRepository firstNameRepository,
                         MiddleNameRepository middleNameRepository,
                         LastNameRepository lastNameRepository) {
        this.firstNameRepository = firstNameRepository;
        this.middleNameRepository = middleNameRepository;
        this.lastNameRepository = lastNameRepository;
    }

    public String generateFirstName() {
        return firstNameRepository.findRandomFirstName();
    }

    public String generateMiddleName() {
        return middleNameRepository.findRandomMiddleName();
    }

    public String generateLastName() {
        return lastNameRepository.findRandomLastName();
    }

    public String generateFullName() {
        return String.format("%s %s %s",
                generateFirstName(),
                generateMiddleName(),
                generateLastName());
    }
}

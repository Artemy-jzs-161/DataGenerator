package ru.temaborovik.generate;

import org.springframework.stereotype.Component;
import ru.temaborovik.model.FirstName;
import ru.temaborovik.model.LastName;
import ru.temaborovik.model.MiddleName;
import ru.temaborovik.repository.FirstNameRepository;
import ru.temaborovik.repository.LastNameRepository;
import ru.temaborovik.repository.MiddleNameRepository;

@Component
public class PersonNameGenerator {
    private final FirstNameRepository firstNameRepository;
    private final MiddleNameRepository middleNameRepository;
    private final LastNameRepository lastNameRepository;

    public PersonNameGenerator(FirstNameRepository firstNameRepository,
                               MiddleNameRepository middleNameRepository,
                               LastNameRepository lastNameRepository) {
        this.firstNameRepository = firstNameRepository;
        this.middleNameRepository = middleNameRepository;
        this.lastNameRepository = lastNameRepository;
    }

    public FirstName generateFirstName() {
        return firstNameRepository.findRandomFirstName();
    }

    public MiddleName generateMiddleName() {
        return middleNameRepository.findRandomMiddleName();
    }

    public LastName generateLastName() {
        return lastNameRepository.findRandomLastName();
    }

    public String generateFullName() {
        return String.format("%s %s %s",
                generateFirstName(),
                generateMiddleName(),
                generateLastName());
    }
}

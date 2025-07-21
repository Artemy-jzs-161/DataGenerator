package ru.temaborovik.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.temaborovik.dto.PersonDto;
import ru.temaborovik.generate.*;
import ru.temaborovik.model.*;
import ru.temaborovik.repository.PersonRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonNameGenerator nameGenerator;
    private final SnilsGenerator snilsGenerator;
    private final InnGenerator innGenerator;
    private final PassportGenerator passportGenerator;
    private final PersonRepository personRepository;

    public PersonDto generateRandomPerson(boolean save) {
        return generateRandomPersonInternal(save);
    }

    public PersonDto generatePersonWithRegion(String regionCode, boolean save) {
        return generatePersonWithRegionInternal(regionCode, save);
    }

    public List<PersonDto> generateRandomPersons(int count, boolean save) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateRandomPersonInternal(save))
                .collect(Collectors.toList());
    }

    public List<PersonDto> generatePersonsWithRegion(String regionCode, int count, boolean save) {
        return IntStream.range(0, count)
                .mapToObj(i -> generatePersonWithRegionInternal(regionCode, save))
                .collect(Collectors.toList());
    }

    private PersonDto generateRandomPersonInternal(boolean save) {
        PersonDto dto = createPersonDto(null);
        if (save) {
            personRepository.save(mapToEntity(dto));
        }
        return dto;
    }

    private PersonDto generatePersonWithRegionInternal(String regionCode, boolean save) {
        PersonDto dto = createPersonDto(regionCode);
        if (save) {
            personRepository.save(mapToEntity(dto));
        }
        return dto;
    }

    private PersonDto createPersonDto(String regionCode) {
        String firstName = nameGenerator.generateFirstName().getFirstName();
        String middleName = nameGenerator.generateMiddleName().getMiddleName();
        String lastName = nameGenerator.generateLastName().getLastName();
        String snils = snilsGenerator.generate();
        String inn = innGenerator.generateForIndividual();
        String passport = regionCode == null
                ? passportGenerator.generateRandom()
                : passportGenerator.generateFromRegionCode(regionCode);

        return PersonDto.builder()
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .snils(snils)
                .inn(inn)
                .passport(passport)
                .build();
    }

    /**
     * Маппинг DTO → Entity для сохранения
     */
    private PersonEntity mapToEntity(PersonDto dto) {
        return PersonEntity.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .snils(dto.getSnils())
                .inn(dto.getInn())
                .passport(dto.getPassport())
                .build();
    }
}
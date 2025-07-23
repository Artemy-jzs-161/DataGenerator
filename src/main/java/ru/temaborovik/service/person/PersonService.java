package ru.temaborovik.service.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.temaborovik.dto.person.PersonDto;
import ru.temaborovik.entity.person.PersonEntity;
import ru.temaborovik.generate.person.InnGenerator;
import ru.temaborovik.generate.person.PassportGenerator;
import ru.temaborovik.generate.person.PersonNameGenerator;
import ru.temaborovik.generate.person.SnilsGenerator;
import ru.temaborovik.repository.person.PersonRepository;

import java.util.List;
import java.util.Optional;
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

    public Optional<PersonDto> getById(Long id) {
        return personRepository.findById(id).map(this::mapToDto);
    }

    public List<PersonDto> getAll() {
        return personRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

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
     * При сохранении в БД
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

    /**
     * При чтении из БД
     */
    private PersonDto mapToDto(PersonEntity entity) {
        return PersonDto.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .middleName(entity.getMiddleName())
                .snils(entity.getSnils())
                .inn(entity.getInn())
                .passport(entity.getPassport())
                .build();
    }
}
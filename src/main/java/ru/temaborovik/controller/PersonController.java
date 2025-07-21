package ru.temaborovik.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.temaborovik.dto.*;
import ru.temaborovik.dto.GeneratePersonListRequest;
import ru.temaborovik.dto.PersonDto;
import ru.temaborovik.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
@Tag(name = "Person API", description = "Генерация данных физических лиц")
@Slf4j
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Сгенерировать одного человека со случайным регионом")
    @PostMapping("/random")
    public PersonDto generateRandomPerson(@RequestBody(required = false) GeneratePersonListRequest request) {
        int count = request == null ? 1 : request.getCount();
        boolean save = request == null || request.isSave();

        validateCount(count);
        log.info("Генерация {} случайных человек, save={}", count, save);

        if (count == 1) {
            PersonDto person = personService.generateRandomPersons(1, save).get(0);
            return person;
        } else {
            throw new IllegalArgumentException("Для генерации списка используйте /random/list");
        }
    }

    @Operation(summary = "Сгенерировать список людей со случайными регионами")
    @PostMapping("/random/list")
    public List<PersonDto> generateRandomPersonList(@RequestBody GeneratePersonListRequest request) {
        validateCount(request.getCount());
        log.info("Генерация списка случайных людей, count={}, save={}", request.getCount(), request.isSave());
        return personService.generateRandomPersons(request.getCount(), request.isSave());
    }

    @Operation(summary = "Сгенерировать одного человека по заданному коду региона")
    @PostMapping("/region")
    public PersonDto generatePersonWithRegion(@RequestBody GeneratePersonByRegionRequest request) {
        validateCount(request.getCount());
        log.info("Генерация {} человек с регионом {}, save={}", request.getCount(), request.getRegionCode(), request.isSave());

        if (request.getCount() == 1) {
            return personService.generatePersonsWithRegion(request.getRegionCode(), 1, request.isSave()).get(0);
        } else {
            throw new IllegalArgumentException("Для генерации списка используйте /region/list");
        }
    }

    @Operation(summary = "Сгенерировать список людей по заданному региону")
    @PostMapping("/region/list")
    public List<PersonDto> generatePersonListWithRegion(@RequestBody GeneratePersonByRegionRequest request) {
        validateCount(request.getCount());
        log.info("Генерация списка людей с регионом {}, count={}, save={}", request.getRegionCode(), request.getCount(), request.isSave());
        return personService.generatePersonsWithRegion(request.getRegionCode(), request.getCount(), request.isSave());
    }

    private void validateCount(int count) {
        if (count < 1 || count > 100) {
            log.warn("Неверное значение count: {}", count);
            throw new IllegalArgumentException("Параметр 'count' должен быть от 1 до 100");
        }
    }
}

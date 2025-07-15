package org.cbr.controller;


import org.cbr.generator.IndividualGenerator;
import org.cbr.model.IndividualModel;
import org.cbr.repository.IndividualRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/individuals")
public class IndividualController {

    private final IndividualRepository repository;
    private final IndividualGenerator generator;

    // Конструктор (можно заменить на @Autowired)
    public IndividualController(IndividualRepository repository,
                                IndividualGenerator generator) {
        this.repository = repository;
        this.generator = generator;
    }

    // 1. Создать новое физлицо
    @PostMapping
    public IndividualModel createIndividual() {
        IndividualModel individualModel = generator.generate();
        return repository.save(individualModel);
    }

    // 2. Получить всех (без пагинации)
    @GetMapping
    public List<IndividualModel> getAllIndividuals() {
        return repository.findAll();
    }

    // 3. Получить по ID
    @GetMapping("/{id}")
    public IndividualModel getById(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElse(null);
    }

    // 4. Удалить по ID
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        repository.deleteById(id);
        return "Физлицо с ID " + id + " удалено";
    }
}
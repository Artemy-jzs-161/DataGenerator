package org.cbr.generator;

import org.cbr.model.Individual;
import org.cbr.repository.IndividualRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class IndividualGenerator {
    private final NameGenerator nameGenerator;
    private final DateGenerator dateGenerator;
    private final SnilsGenerator snilsGenerator;
    private final InnGenerator innGenerator;
    private final IndividualRepository repository;

    // Конструктор с зависимостями
    public IndividualGenerator(NameGenerator nameGenerator,
                               DateGenerator dateGenerator,
                               SnilsGenerator snilsGenerator,
                               InnGenerator innGenerator,
                               IndividualRepository repository) {
        this.nameGenerator = nameGenerator;
        this.dateGenerator = dateGenerator;
        this.snilsGenerator = snilsGenerator;
        this.innGenerator = innGenerator;
        this.repository = repository;
    }

    @Transactional
    public Individual generateAndSave() {
        Individual individual;
        do {
            individual = generate();
        } while (repository.existsByInn(individual.getInn()) ||
                repository.existsBySnils(individual.getSnils()));

        return repository.save(individual);
    }

    public Individual generate() {
        Individual individual = new Individual();
        individual.setFirstName(nameGenerator.generateFirstName());
        individual.setLastName(nameGenerator.generateLastName());
        individual.setMiddleName(nameGenerator.generateMiddleName());
        individual.setBirthDate(dateGenerator.generateBirthDate(18, 70));
        individual.setSnils(snilsGenerator.generate());
        individual.setInn(innGenerator.generateForIndividual());

        return individual;
    }
}
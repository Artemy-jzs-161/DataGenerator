package org.cbr.generator;

import org.cbr.model.IndividualModel;
import org.cbr.repository.IndividualRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class IndividualGenerator {
    private final IndividualNameGenerator individualNameGenerator;
    private final DateGenerator dateGenerator;
    private final SnilsGenerator snilsGenerator;
    private final InnGenerator innGenerator;
    private final IndividualRepository repository;

    // Конструктор с зависимостями
    public IndividualGenerator(IndividualNameGenerator individualNameGenerator,
                               DateGenerator dateGenerator,
                               SnilsGenerator snilsGenerator,
                               InnGenerator innGenerator,
                               IndividualRepository repository) {
        this.individualNameGenerator = individualNameGenerator;
        this.dateGenerator = dateGenerator;
        this.snilsGenerator = snilsGenerator;
        this.innGenerator = innGenerator;
        this.repository = repository;
    }

    @Transactional
    public IndividualModel generateAndSave() {
        IndividualModel individualModel;
        do {
            individualModel = generate();
        } while (repository.existsByInn(individualModel.getInn()) ||
                repository.existsBySnils(individualModel.getSnils()));

        return repository.save(individualModel);
    }

    public IndividualModel generate() {
        IndividualModel individualModel = new IndividualModel();
        individualModel.setFirstName(individualNameGenerator.generateFirstName());
        individualModel.setLastName(individualNameGenerator.generateLastName());
        individualModel.setMiddleName(individualNameGenerator.generateMiddleName());
        individualModel.setBirthDate(dateGenerator.generateBirthDate(18, 70));
        individualModel.setSnils(snilsGenerator.generate());
        individualModel.setInn(innGenerator.generateForIndividual());

        return individualModel;
    }
}
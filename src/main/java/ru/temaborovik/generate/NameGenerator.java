package ru.temaborovik.generate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.temaborovik.enums.BusinessForms;
import ru.temaborovik.model.OrganizationName;
import ru.temaborovik.model.PrefixName;
import ru.temaborovik.model.StemsName;
import ru.temaborovik.model.SuffixName;
import ru.temaborovik.repository.*;

@Component
@RequiredArgsConstructor
public class NameGenerator {

    private final PrefixNameRepository prefixNameRepository;
    private final StemsNameRepository stemsNameRepository;
    private final SuffixNameRepository suffixNameRepository;
    private final OrganizationNameRepository organizationNameRepository;
    private final PersonNameGenerator personNameGenerator;

    public String generateOrganizationName(BusinessForms form) {
        if (form == BusinessForms.IP) {
            return String.format(
                    "%s %s",
                    form.getFormName(),
                    personNameGenerator.generateFullName());
        } else {
            String uniqueName;
            int attempts = 0;

            do {
                uniqueName = String.format(
                        "%s \"%s\"",
                        form.getFormName(),
                        generateName());
                attempts++;
            } while (organizationNameRepository
                    .existsByName(uniqueName) && attempts < 10);

            if (organizationNameRepository.existsByName(uniqueName)) {
                throw new RuntimeException("Не удалось сгенерировать уникальное название организации");
            }

            organizationNameRepository
                    .save(OrganizationName.builder()
                            .name(uniqueName)
                            .build());

            return uniqueName;
        }
    }


    private PrefixName generatePrefixName() {
        return prefixNameRepository.findRandomPrefixName();
    }

    private StemsName generateStemsName() {
        return stemsNameRepository.findRandomStemsName();
    }

    private SuffixName generateSuffixName() {
        return suffixNameRepository.findRandomSuffixName();
    }

    private String generateName() {
        return String.format("%s%s%s",
                generatePrefixName(),
                generateStemsName(),
                generateSuffixName());
    }
}

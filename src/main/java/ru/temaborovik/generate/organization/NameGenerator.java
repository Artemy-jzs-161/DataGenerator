package ru.temaborovik.generate.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.temaborovik.enums.BusinessForms;
import ru.temaborovik.generate.person.PersonNameGenerator;
import ru.temaborovik.entity.organization.OrganizationEntity;
import ru.temaborovik.entity.organization.PrefixName;
import ru.temaborovik.entity.organization.StemsName;
import ru.temaborovik.entity.organization.SuffixName;
import ru.temaborovik.repository.organization.OrganizationRepository;
import ru.temaborovik.repository.organization.PrefixNameRepository;
import ru.temaborovik.repository.organization.StemsNameRepository;
import ru.temaborovik.repository.organization.SuffixNameRepository;

@Component
@RequiredArgsConstructor
public class NameGenerator {

    private final PrefixNameRepository prefixNameRepository;
    private final StemsNameRepository stemsNameRepository;
    private final SuffixNameRepository suffixNameRepository;
    private final OrganizationRepository organizationRepository;
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
            } while (organizationRepository
                    .existsByName(uniqueName) && attempts < 10);

            if (organizationRepository.existsByName(uniqueName)) {
                throw new RuntimeException("Не удалось сгенерировать уникальное название организации");
            }

            organizationRepository
                    .save(OrganizationEntity.builder()
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

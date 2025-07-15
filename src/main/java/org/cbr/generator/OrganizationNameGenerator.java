package org.cbr.generator;

import org.cbr.enums.BusinessForms;
import org.cbr.repository.companyname.PrefixNameRepository;
import org.cbr.repository.companyname.StemsNameRepository;
import org.cbr.repository.companyname.SuffixNameRepository;
import org.springframework.stereotype.Component;

@Component
public class OrganizationNameGenerator {
    private final PrefixNameRepository prefixNameRepository;
    private final StemsNameRepository stemsNameRepository;
    private final SuffixNameRepository suffixNameRepository;

    private IndividualNameGenerator individualNameGenerator;

    public OrganizationNameGenerator(PrefixNameRepository prefixNameRepository,
                                     StemsNameRepository stemsNameRepository,
                                     SuffixNameRepository suffixNameRepository) {
        this.prefixNameRepository = prefixNameRepository;
        this.stemsNameRepository = stemsNameRepository;
        this.suffixNameRepository = suffixNameRepository;
    }

    public String generateOrganizationName(BusinessForms forms) {
        if (forms == BusinessForms.IP) {
            return String.format(
                    "%s %s",
                    forms.getFormName(),
                    individualNameGenerator.generateFullName());
        } else {
            return String.format("%s \"%s\"",
                    forms.getFormName(),
                    generateName());
        }
    }

    public String generatePrefixName() {
        return prefixNameRepository.findRandomPrefixName();
    }

    public String generateStemsName() {
        return stemsNameRepository.findRandomStepsName();
    }

    public String generateSuffixName() {
        return suffixNameRepository.findRandomSuffixName();
    }

    public String generateName() {
        return String.format("%s%s%s",
                generatePrefixName(),
                generateStemsName(),
                generateSuffixName());
    }
}

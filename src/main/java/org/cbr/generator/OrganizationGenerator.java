package org.cbr.generator;


import org.cbr.enums.BusinessForms;
import org.cbr.model.OrganizationModel;
import org.springframework.stereotype.Component;

@Component
public class OrganizationGenerator {
    private final OrganizationNameGenerator organizationNameGenerator;
    private final InnGenerator innGenerator;
    private final KppGenerator kppGenerator;


    public OrganizationGenerator(OrganizationNameGenerator organizationNameGenerator,
                                 InnGenerator innGenerator,
                                 KppGenerator kppGenerator) {
        this.organizationNameGenerator = organizationNameGenerator;
        this.innGenerator = innGenerator;
        this.kppGenerator = kppGenerator;
    }

    public OrganizationModel generate(BusinessForms forms) {
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setName(organizationNameGenerator.generateOrganizationName(forms));
        organizationModel.setInn(innGenerator.generateEntryInn());

        return organizationModel;
    }



}

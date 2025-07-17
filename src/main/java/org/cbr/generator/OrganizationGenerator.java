package org.cbr.generator;


import org.cbr.enums.BusinessForms;
import org.cbr.generator.inn.InnGenerator;
import org.cbr.generator.kpp.KppGenerator;
import org.cbr.generator.ogrn.OgrnGenerator;
import org.cbr.model.OrganizationModel;
import org.springframework.stereotype.Component;

@Component
public class OrganizationGenerator {
    private final OrganizationNameGenerator organizationNameGenerator;
    private final InnGenerator innGenerator;
    private final KppGenerator kppGenerator;
    private final OgrnGenerator ogrnGenerator;


    public OrganizationGenerator(OrganizationNameGenerator organizationNameGenerator,
                                 InnGenerator innGenerator,
                                 KppGenerator kppGenerator,
                                 OgrnGenerator ogrnGenerator) {
        this.organizationNameGenerator = organizationNameGenerator;
        this.innGenerator = innGenerator;
        this.kppGenerator = kppGenerator;
        this.ogrnGenerator = ogrnGenerator;
    }

    public OrganizationModel generate(BusinessForms forms) {
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setName(organizationNameGenerator.generateOrganizationName(forms));
        organizationModel.setInn(innGenerator.generateEntryInn());
        organizationModel.setKpp(kppGenerator.generate());
        organizationModel.setOgrn(ogrnGenerator.generateOrganizationOgrn());

        return organizationModel;
    }



}

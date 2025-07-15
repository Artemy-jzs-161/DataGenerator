package org.cbr.controller;


import jakarta.validation.constraints.NotNull;
import org.cbr.enums.BusinessForms;
import org.cbr.generator.OrganizationGenerator;
import org.cbr.model.OrganizationModel;
import org.cbr.repository.OrganizationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    private final OrganizationRepository repository;
    private final OrganizationGenerator generator;

    public OrganizationController(OrganizationRepository organizationRepository,
                                  OrganizationGenerator organizationGenerator) {
        this.repository = organizationRepository;
        this.generator = organizationGenerator;
    }

    @PostMapping
    public ResponseEntity<OrganizationModel> createOrganization(
            @RequestParam @NotNull BusinessForms forms) {
        try {
            OrganizationModel organizationModel = generator.generate(forms);
            return ResponseEntity.ok(repository.save(organizationModel));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }
}

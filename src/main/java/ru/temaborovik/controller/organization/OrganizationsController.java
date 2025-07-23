package ru.temaborovik.controller.organization;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.temaborovik.dto.organization.OrganizationsDto;
import ru.temaborovik.dto.organization.OrganizationsRequest;
import ru.temaborovik.service.organization.OrganizationsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
@Tag(name = "Organization API", description = "Генерация и управление организациями")
@Slf4j
public class OrganizationsController {
    private final OrganizationsService organizationsService;

    @Operation(summary = "Сгенерировать одну организацию")
    @PostMapping("/generate")
    public OrganizationsDto generateOrganization(
            @RequestBody @Valid OrganizationsRequest request) {
        log.info("Генерация организации: форма={}, save={}", request.getBusinessForm(), request.isSave());
        return organizationsService.generateOrganization(request.getBusinessForm(), request.isSave());
    }

    @Operation(summary = "Сгенерировать список организаций")
    @PostMapping("/generate/list")
    public List<OrganizationsDto> generateOrganizations(
            @RequestBody @Valid OrganizationsRequest request) {
        log.info("Генерация {} организаций: форма={}, save={}", request.getCount(), request.getBusinessForm(), request.isSave());
        return organizationsService.generateOrganizations(request.getBusinessForm(), request.getCount(), request.isSave());
    }

    @Operation(summary = "Получить организацию по ID")
    @GetMapping("/{id}")
    public Optional<OrganizationsDto> getOrganizationById(@PathVariable Long id) {
        return organizationsService.getById(id);
    }

    @Operation(summary = "Получить список всех организаций")
    @GetMapping
    public List<OrganizationsDto> getAllOrganizations() {
        return organizationsService.getAllOrganizations();
    }

    @Operation(summary = "Удалить все организации")
    @DeleteMapping
    public ResponseEntity<Void> deleteAllOrganizations() {
        organizationsService.deleteAllOrganizations();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Удалить организацию по ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizationById(@PathVariable Long id) {
        organizationsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

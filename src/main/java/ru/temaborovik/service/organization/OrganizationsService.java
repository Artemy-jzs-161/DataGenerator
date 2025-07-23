package ru.temaborovik.service.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.temaborovik.dto.organization.OrganizationsDto;
import ru.temaborovik.entity.Region;
import ru.temaborovik.entity.organization.OrganizationEntity;
import ru.temaborovik.enums.BusinessForms;
import ru.temaborovik.generate.organization.KppGenerator;
import ru.temaborovik.generate.organization.NameGenerator;
import ru.temaborovik.generate.organization.OgrnGenerator;
import ru.temaborovik.repository.RegionRepository;
import ru.temaborovik.repository.organization.OrganizationRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrganizationsService {
    private final NameGenerator nameGenerator;
    private final KppGenerator kppGenerator;
    private final OgrnGenerator ogrnGenerator;
    private final OrganizationRepository organizationRepository;
    private final RegionRepository regionRepository;


    public List<OrganizationsDto> generateOrganizations(BusinessForms form, int count, boolean save) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateOrganization(form, save))
                .collect(Collectors.toList());
    }

    public OrganizationsDto generateOrganization(BusinessForms form, boolean save) {
        Region region = regionRepository.findRandomRegion();

        OrganizationsDto dto = OrganizationsDto.builder()
                .name(nameGenerator.generateOrganizationName(form))
                .kpp(kppGenerator.generate(region.getCode()))
                .ogrn(form == BusinessForms.IP
                        ? ogrnGenerator.generateIpOgrn(region.getCode())
                        : ogrnGenerator.generateOrganizationOgrn(region.getCode()))
                .build();

        if (save) {
            organizationRepository.save(mapToEntity(dto));
        }
        return dto;
    }

    public Optional<OrganizationsDto> getById(Long id) {
        return organizationRepository.findById(id)
                .map(this::mapToDto);
    }

    public List<OrganizationsDto> getAllOrganizations() {
        return organizationRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        if (!organizationRepository.existsById(id)) {
            throw new NoSuchElementException("Организация с ID " + id + " не найдена.");
        }
        organizationRepository.deleteById(id);
    }

    public void deleteAllOrganizations() {
        organizationRepository.deleteAll();
    }

    private OrganizationEntity mapToEntity(OrganizationsDto dto) {
        return OrganizationEntity.builder()
                .name(dto.getName())
                .kpp(dto.getKpp())
                .ogrn(dto.getOgrn())
                .build();
    }

    private OrganizationsDto mapToDto(OrganizationEntity entity) {
        return OrganizationsDto.builder()
                .name(entity.getName())
                .kpp(entity.getKpp())
                .ogrn(entity.getOgrn())
                .build();
    }
}

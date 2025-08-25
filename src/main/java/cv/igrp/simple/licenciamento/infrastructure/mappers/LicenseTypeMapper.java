package cv.igrp.simple.licenciamento.infrastructure.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cv.igrp.simple.licenciamento.application.dto.LicenseTypeResponseDTO;
import cv.igrp.simple.licenciamento.domain.license2.models.LicenseType;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import cv.igrp.simple.shared.infrastructure.persistence.entity.CategoryEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.LicenseTypeEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LicenseTypeMapper {

    private final MetadataMapper metadataMapper;

    public LicenseTypeMapper(MetadataMapper metadataMapper) {
        this.metadataMapper = metadataMapper;
    }


    public LicenseType toDomain(LicenseTypeEntity entity) {
        return LicenseType.reconstruir(
                Identificador.from(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getCode(),
                entity.getLicensingModelKey(),
                entity.getValidityPeriod(),
                entity.getValidityUnitKey(),
                entity.isRenewable(),
                entity.isAutoRenewal(),
                entity.isRequiresInspection(),
                entity.isRequiresPublicConsultation(),
                entity.getMaxProcessingDays(),
                entity.isHasFees(),
                entity.getBaseFee(),
                entity.getCurrencyCode(),
                entity.isActive(),
                metadataMapper.toDomain(entity.getMetadata()),
                entity.getSortOrder(),
                entity.getCategoryId() != null ? Identificador.from(entity.getCategoryId().getId()) : null
        );
    }

    public LicenseTypeEntity toEntity(LicenseType domain) {
        LicenseTypeEntity entity = new LicenseTypeEntity();
        entity.setId(domain.getId().getValor());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setCode(domain.getCode());
        entity.setLicensingModelKey(domain.getLicensingModelKey());
        entity.setValidityPeriod(domain.getValidityPeriod());
        entity.setValidityUnitKey(domain.getValidityUnitKey());
        entity.setRenewable(domain.isRenewable());
        entity.setAutoRenewal(domain.isAutoRenewal());
        entity.setRequiresInspection(domain.isRequiresInspection());
        entity.setRequiresPublicConsultation(domain.isRequiresPublicConsultation());
        entity.setMaxProcessingDays(domain.getMaxProcessingDays());
        entity.setHasFees(domain.isHasFees());
        entity.setBaseFee(domain.getBaseFee());
        entity.setCurrencyCode(domain.getCurrencyCode());
        entity.setActive(domain.isAtivo());
        entity.setSortOrder(domain.getSortOrder());
        entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));

        // Relacionamento com Category
        if (domain.getCategoryId() != null) {
            CategoryEntity parentEntity = new CategoryEntity();
            entity.setCategoryId(parentEntity);
        }
        return entity;
    }

    public LicenseTypeResponseDTO toResponseDTO(LicenseType licenseType) {
        if (licenseType == null) return null;

        LicenseTypeResponseDTO dto = new LicenseTypeResponseDTO();
        dto.setId(licenseType.getId().getStringValor());
        dto.setName(licenseType.getName());
        dto.setDescription(licenseType.getDescription());
        dto.setCode(licenseType.getCode());
        dto.setCategoryId(licenseType.getCategoryId() != null
                ? licenseType.getCategoryId().getStringValor() : null);
        dto.setLicensingModelKey(licenseType.getLicensingModelKey());
        dto.setValidityPeriod(licenseType.getValidityPeriod());
        dto.setValidityUnitKey(licenseType.getValidityUnitKey());
        dto.setRenewable(licenseType.isRenewable());
        dto.setAutoRenewal(licenseType.isAutoRenewal());
        dto.setRequiresInspection(licenseType.isRequiresInspection());
        dto.setRequiresPublicConsultation(licenseType.isRequiresPublicConsultation());
        dto.setMaxProcessingDays(licenseType.getMaxProcessingDays());
        dto.setHasFees(licenseType.isHasFees());
        dto.setBaseFee(licenseType.getBaseFee());
        dto.setCurrencyCode(licenseType.getCurrencyCode());
        dto.setMetadata(licenseType.getMetadata() != null
                ? licenseType.getMetadata().getValores() : new HashMap<>());

        return dto;
    }

}

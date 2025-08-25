package cv.igrp.simple.licenciamento.infrastructure.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cv.igrp.simple.licenciamento.domain.license2.models.Sector;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import cv.igrp.simple.shared.infrastructure.persistence.entity.SectorEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SectorMapper {

    private final MetadataMapper metadataMapper;

    public SectorMapper(MetadataMapper metadataMapper) {
        this.metadataMapper = metadataMapper;
    }

    public Sector toDomain(SectorEntity entity) {
        return Sector.reconstruir(
                Identificador.from(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getSectorTypeKey(),
                entity.getCode(),
                entity.isActive(),
                entity.getSortOrder(),
                metadataMapper.toDomain(entity.getMetadata())
        );
    }

    public SectorEntity toEntity(Sector domain) {
        SectorEntity entity = new SectorEntity();
        entity.setId(domain.getId().getValor());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setSectorTypeKey(domain.getSectorTypeKey());
        entity.setCode(domain.getCode());
        entity.setActive(domain.isAtivo());
        entity.setSortOrder(domain.getSortOrder());
        entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));
        return entity;
    }
}

package cv.igrp.simple.licenciamento.infrastructure.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import cv.igrp.simple.licenciamento.domain.license2.models.Category;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import cv.igrp.simple.shared.infrastructure.persistence.entity.CategoryEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.SectorEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategoryMapper {

    private final MetadataMapper metadataMapper;

    public CategoryMapper(MetadataMapper metadataMapper) {
        this.metadataMapper = metadataMapper;
    }

    public Category toDomain(CategoryEntity entity) {
        return Category.reconstruir(
                Identificador.from(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getCode(),
                entity.isActive(),
                entity.getLevel(),
                entity.getSortOrder(),
                metadataMapper.toDomain(entity.getMetadata()),
                entity.getPath(),
                entity.getParentId() != null ? Identificador.from(entity.getParentId().getId()) : null,
                entity.getSectorId() != null ? Identificador.from(entity.getSectorId().getId()) : null
        );
    }

    public CategoryEntity toEntity(Category domain) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(domain.getId().getValor());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setCode(domain.getCode());
        entity.setActive(domain.isAtivo());
        entity.setLevel(domain.getLevel());
        entity.setSortOrder(domain.getSortOrder());
        entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));
        entity.setPath(domain.getPath());

        if (domain.getParentId() != null) {
            CategoryEntity parentEntity = new CategoryEntity();
            parentEntity.setId(domain.getParentId().getValor());
            entity.setParentId(parentEntity);
        }

        if (domain.getSectorId() != null) {
            SectorEntity sectorEntity = new SectorEntity();
            sectorEntity.setId(domain.getSectorId().getValor());
            entity.setSectorId(sectorEntity);
        }

        return entity;
    }
}

package cv.igrp.simple.licenciamento.infrastructure.mappers;

import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;
import cv.igrp.simple.licenciamento.domain.models.Classe;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.ClasseEntity;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper {

    public Classe toDomain(ClasseEntity entity) {
        if (entity == null) {
            return null;
        }
        return Classe.reconstruir(
                entity.getId(), // ID do banco (Integer)
                Identificador.from(entity.getExternalId()),
                entity.getClasse(),
                entity.getDescricao(),
                entity.getEstado()
        );
    }

    public ClasseEntity toEntity(Classe classe) {
        if (classe == null) {
            return null;
        }

        var entity = new ClasseEntity();
        if (classe.getId() != null) {
            entity.setId(classe.getId());
        }
        entity.setExternalId(classe.getIdClasse().getValor());
        entity.setClasse(classe.getClasse());
        entity.setDescricao(classe.getDescricao());
        entity.setEstado(classe.getEstado());

        return entity;
    }

    public ClasseResponseDTO toDTO(Classe classe) {
        if (classe == null) {
            return null;
        }

        var dto = new ClasseResponseDTO();
        dto.setClasseId(classe.getIdClasse().getStringValor());
        dto.setCodigo(classe.getClasse());
        dto.setDescricao(classe.getDescricao());
        dto.setEstado(classe.getEstado() != null ? classe.getEstado().name() : "");
        dto.setEstadoDesc(classe.getEstado() != null ? classe.getEstado().getDescription() : "");

        return dto;
    }
}

package cv.igrp.simple.licenciamento.infrastructure.mappers;

import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeResponseDTO;
import cv.igrp.simple.licenciamento.domain.models.TipoAtividade;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.TipoAtividadeEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoAtividadeMapper {

    public TipoAtividade toDomain(TipoAtividadeEntity entity) {
        if (entity == null) return null;

        return TipoAtividade.reconstruir(
                entity.getId(), // <-- agora passando o ID inteiro
                Identificador.from(entity.getExternalId()),
                entity.getNome(),
                entity.getCodigo(),
                entity.getDescricao(),
                entity.getEstado()
        );
    }

    public TipoAtividadeEntity toEntity(TipoAtividade tipoAtividade) {
        if (tipoAtividade == null) return null;

        var entity = new TipoAtividadeEntity();
        entity.setExternalId(tipoAtividade.getIdTipoAtividade().getValor());
        entity.setNome(tipoAtividade.getNome());
        entity.setCodigo(tipoAtividade.getCodigo());
        entity.setDescricao(tipoAtividade.getDescricao());
        entity.setEstado(tipoAtividade.getEstado());

        if (tipoAtividade.getId()!=null){
          entity.setId(tipoAtividade.getId());
        }

        return entity;
    }

    public TipoAtividadeResponseDTO toDTO(TipoAtividade tipoAtividade) {
        if (tipoAtividade == null) {
            return null;
        }

        var dto = new TipoAtividadeResponseDTO();

        dto.setTipoAtividadeId(tipoAtividade.getIdTipoAtividade().getStringValor());

        dto.setNome(tipoAtividade.getNome());
        dto.setCodigo(tipoAtividade.getCodigo());
        dto.setDescricao(tipoAtividade.getDescricao());

        dto.setEstado(tipoAtividade.getEstado() != null
                ? tipoAtividade.getEstado().name()
                : "");

        dto.setEstadoDesc(tipoAtividade.getEstado() != null
                ? tipoAtividade.getEstado().getDescription()
                : "");

        return dto;
    }
}

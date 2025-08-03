package cv.igrp.simple.licenciamento.infrastructure.mappers;

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
                entity.getCodigo(),
                entity.getDescricao(),
                entity.getEstado()
        );
    }

    public TipoAtividadeEntity toEntity(TipoAtividade tipoAtividade) {
        if (tipoAtividade == null) return null;

        var entity = new TipoAtividadeEntity();
        entity.setExternalId(tipoAtividade.getIdTipoAtividade().getValor());
        entity.setCodigo(tipoAtividade.getCodigo());
        entity.setDescricao(tipoAtividade.getDescricao());
        entity.setEstado(tipoAtividade.getEstado());

        if (tipoAtividade.getId()!=null){
          entity.setId(tipoAtividade.getId());
        }

        return entity;
    }
}

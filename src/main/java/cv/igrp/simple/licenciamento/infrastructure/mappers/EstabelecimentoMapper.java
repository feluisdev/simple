package cv.igrp.simple.licenciamento.infrastructure.mappers;

import cv.igrp.simple.licenciamento.domain.models.Estabelecimento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.EstabelecimentoEntity;
import org.springframework.stereotype.Component;

@Component
public class EstabelecimentoMapper {

    public Estabelecimento toDomain(EstabelecimentoEntity entity) {
        if (entity == null) return null;

        return Estabelecimento.reconstruir(
                entity.getId(), // id Integer
                Identificador.from(entity.getExternalId()), // idEstabelecimento Identificador
                entity.getGerente(),
                entity.getDescricao(),
                entity.isFlagVistoria(),
                entity.isLicRetalho(),
                entity.getEndereco(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getNif(),
                entity.getEstado()
        );
    }

    public EstabelecimentoEntity toEntity(Estabelecimento estabelecimento) {
        if (estabelecimento == null) return null;

        var entity = new EstabelecimentoEntity();

        if (estabelecimento.getId() != null) {
            entity.setId(estabelecimento.getId());
        }

        entity.setExternalId(estabelecimento.getIdEstabelecimento().getValor());
        entity.setGerente(estabelecimento.getGerente());
        entity.setDescricao(estabelecimento.getDescricao());
        entity.setFlagVistoria(estabelecimento.isFlagVistoria());
        entity.setLicRetalho(estabelecimento.isLicRetalho());
        entity.setEndereco(estabelecimento.getEndereco());
        entity.setTelefone(estabelecimento.getTelefone());
        entity.setEmail(estabelecimento.getEmail());
        entity.setNif(estabelecimento.getNif());
        entity.setEstado(estabelecimento.getEstado());

        return entity;
    }
}

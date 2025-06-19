package cv.igrp.simple.configuracoes.infrastructure.mappers;

import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.valueobject.CategoriaUuid;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriaServicoEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaServicoEntity toEntity(CategoriaServico domain) {
        if (domain == null) return null;

        CategoriaServicoEntity entity = new CategoriaServicoEntity();

        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setDescricao(domain.getDescricao());
        entity.setIcon(domain.getIcone());
        entity.setCor(domain.getCor());
        entity.setOrdem(domain.getOrdem());
        entity.setEstado(domain.isEstado());
        entity.setCategoriaUuid(domain.getCategoriaUuid().getValue());

        entity.setTiposservicos(null);

        return entity;
    }

    public CategoriaServico toDomain(CategoriaServicoEntity entity) {
        if (entity == null) return null;

        return CategoriaServico.reconstruir(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getIcon(),
                entity.getCor(),
                entity.getOrdem(),
                entity.isEstado(),
                CategoriaUuid.from(entity.getCategoriaUuid()),
                null
        );
    }
}

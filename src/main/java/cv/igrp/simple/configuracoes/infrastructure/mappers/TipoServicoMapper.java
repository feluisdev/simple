package cv.igrp.simple.configuracoes.infrastructure.mappers;

import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.valueobject.TipoServicoUuid;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.TipoServicoEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoServicoMapper {

    private final CategoriaMapper categoriaMapper;

    public TipoServicoMapper(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
    }

    public TipoServicoEntity toEntity(TipoServico domain) {
        if (domain == null) return null;

        TipoServicoEntity entity = new TipoServicoEntity();
        entity.setId(domain.getId());
        entity.setCodigo(domain.getCodigo());
        entity.setNome(domain.getNome());
        entity.setDescricao(domain.getDescricao());
        entity.setPrazoEstimado(domain.getPrazoEstimado());
        entity.setValorBase(domain.getValorBase());
        entity.setVistoria(domain.isVistoria());
        entity.setAnaliseTecnica(domain.isAnaliseTecnica());
        entity.setAprovacao(domain.isAprovacao());
        entity.setPortal(domain.isPortal());
        entity.setEstado(domain.isEstado());
        entity.setTipoServicoUuid(domain.getTipoServicoUuid().getValue());

        entity.setCategoriaId(categoriaMapper.toEntity(domain.getCategoria()));

        return entity;
    }

    public TipoServico toDomain(TipoServicoEntity entity) {
        if (entity == null) return null;

        return TipoServico.reconstruir(
                entity.getId(),
                entity.getCodigo(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPrazoEstimado(),
                entity.getValorBase(),
                entity.isVistoria(),
                entity.isAnaliseTecnica(),
                entity.isAprovacao(),
                entity.isPortal(),
                entity.isEstado(),
                categoriaMapper.toDomain(entity.getCategoriaId()),
                TipoServicoUuid.from(entity.getTipoServicoUuid())
        );
    }

}

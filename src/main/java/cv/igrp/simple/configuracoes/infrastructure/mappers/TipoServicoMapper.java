package cv.igrp.simple.configuracoes.infrastructure.mappers;

import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.CategoriaServicoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.TipoServicoEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoServicoMapper {


    public TipoServicoEntity toEntity(TipoServico domain, CategoriaServicoEntity categoriaEntity) {
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
        entity.setTipoServicoUuid(domain.getTipoServicoUuid().getValor());

        entity.setCategoriaId(categoriaEntity);
        return entity;
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
        entity.setTipoServicoUuid(domain.getTipoServicoUuid().getValor());

        entity.setCategoriaId(null);
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
                null,
                Identificador.from(entity.getTipoServicoUuid())
        );
    }


    public TipoServico toDomainWithCategoria(TipoServicoEntity entity, CategoriaServico categoria) {
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
                categoria,
                Identificador.from(entity.getTipoServicoUuid())
        );
    }

}

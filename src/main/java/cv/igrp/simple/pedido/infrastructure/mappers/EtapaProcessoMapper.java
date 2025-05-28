package cv.igrp.simple.pedido.infrastructure.mappers;

import cv.igrp.simple.pedido.domain.models.EtapaProcesso;
import cv.igrp.simple.pedido.infrastructure.persistence.entity.EtapaProcessoEntity;
import org.springframework.stereotype.Component;

@Component
public class EtapaProcessoMapper {

    private final TipoPedidoMapper tipoPedidoMapper;

    public EtapaProcessoMapper(TipoPedidoMapper tipoPedidoMapper) {
        this.tipoPedidoMapper = tipoPedidoMapper;
    }

    public EtapaProcessoEntity toEntity(EtapaProcesso domain) {
        var entity = new EtapaProcessoEntity();
        entity.setId(domain.getId());
        entity.setTipoPedidoId(tipoPedidoMapper.toEntity(domain.getTipoPedido()));
        entity.setCodigo(domain.getCodigo());
        entity.setNome(domain.getNome());
        entity.setDescricao(domain.getDescricao());
        entity.setOrdem(domain.getOrdem());
        entity.setTempoEstimado(domain.getTempoEstimado());
        entity.setRequerDocumento(domain.isRequerDocumento());
        entity.setRequerPagamento(domain.isRequerPagamento());
        entity.setRequerAprovacao(domain.isRequerAprovacao());

        if (domain.getEtapaAnterior() != null) {
            entity.setEtapaAnteriorId(this.toEntity(domain.getEtapaAnterior()));
        }

        return entity;
    }

    public EtapaProcesso toDomain(EtapaProcessoEntity entity) {
        return new EtapaProcesso(
                entity.getId(),
                tipoPedidoMapper.toDomain(entity.getTipoPedidoId()),
                entity.getCodigo(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getOrdem(),
                entity.getTempoEstimado(),
                entity.isRequerDocumento(),
                entity.isRequerPagamento(),
                entity.isRequerAprovacao(),
                entity.getEtapaAnteriorId() != null ? toDomain(entity.getEtapaAnteriorId()) : null
        );
    }
}

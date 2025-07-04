package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.pedidos.domain.models.Avaliacao;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.AvaliacaoPedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {

    private final PedidoMapper pedidoMapper;

    public AvaliacaoMapper(PedidoMapper pedidoMapper) {
        this.pedidoMapper = pedidoMapper;
    }

    public  AvaliacaoPedidoEntity toEntity(Avaliacao avaliacao) {
        AvaliacaoPedidoEntity entity = new AvaliacaoPedidoEntity();

        entity.setId(avaliacao.getIdDb());
        entity.setAvaliacaoUuid(avaliacao.getAvaliacaoUuid().getValor());
        entity.setNota(avaliacao.getNota());
        entity.setComentario(avaliacao.getComentario());
        entity.setDataAvaliacao(avaliacao.getDataAvaliacao());
        entity.setUserId(avaliacao.getUserId());
        entity.setPedidoId(pedidoMapper.toEntity(avaliacao.getPedido()));

        return entity;
    }

    public  Avaliacao toDomain(AvaliacaoPedidoEntity entity) {
        return Avaliacao.reconstruir(
                entity.getId(),
                Identificador.from(entity.getAvaliacaoUuid()),
                entity.getNota(),
                entity.getComentario(),
                entity.getDataAvaliacao(),
                entity.getUserId(),
                pedidoMapper.toDomain(entity.getPedidoId())
        );
    }
}

package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;
import cv.igrp.simple.pedidos.domain.models.Avaliacao;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.AvaliacaoPedidoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class AvaliacaoMapper {



    public  AvaliacaoPedidoEntity toEntity(Avaliacao avaliacao) {
        AvaliacaoPedidoEntity entity = new AvaliacaoPedidoEntity();

        entity.setId(avaliacao.getIdDb());
        entity.setAvaliacaoUuid(avaliacao.getAvaliacaoUuid().getValor());
        entity.setNota(avaliacao.getNota());
        entity.setComentario(avaliacao.getComentario());
        entity.setDataAvaliacao(avaliacao.getDataAvaliacao());
        entity.setUserId(avaliacao.getUserId());
        entity.setPedidoId(null);

        return entity;
    }

    public  AvaliacaoPedidoEntity toEntity(Avaliacao avaliacao, PedidoEntity pedidoEntity) {
        AvaliacaoPedidoEntity entity = new AvaliacaoPedidoEntity();

        entity.setId(avaliacao.getIdDb());
        entity.setAvaliacaoUuid(avaliacao.getAvaliacaoUuid().getValor());
        entity.setNota(avaliacao.getNota());
        entity.setComentario(avaliacao.getComentario());
        entity.setDataAvaliacao(avaliacao.getDataAvaliacao());
        entity.setUserId(avaliacao.getUserId());
        entity.setPedidoId(pedidoEntity);

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
                null
        );
    }

    public  Avaliacao toDomainWithPedido(AvaliacaoPedidoEntity entity, Pedido pedido) {
        return Avaliacao.reconstruir(
                entity.getId(),
                Identificador.from(entity.getAvaliacaoUuid()),
                entity.getNota(),
                entity.getComentario(),
                entity.getDataAvaliacao(),
                entity.getUserId(),
                pedido
        );
    }

    public AvaliacaoPedidoResponseDTO toResponseDTO(Avaliacao avaliacao) {
        if (avaliacao == null) {
            return null;
        }

        return new AvaliacaoPedidoResponseDTO(
                avaliacao.getAvaliacaoUuid().getStringValor(),
                avaliacao.getPedido().getPedidoUuid().getStringValor(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao(),
                avaliacao.getUserId(),
                "nome" // TODO: resolver nome do usuário real, se disponível
        );
    }
}

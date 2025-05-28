package cv.igrp.simple.pedido.infrastructure.mappers;

import cv.igrp.simple.pedido.domain.models.TipoPedido;
import cv.igrp.simple.pedido.infrastructure.persistence.entity.CategoriaPedidoEntity;
import cv.igrp.simple.pedido.infrastructure.persistence.entity.TipoPedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class TipoPedidoMapper {

    private final CategoriaMapper categoriaMapper;

    public TipoPedidoMapper(CategoriaMapper categoriaMapper) {
        this.categoriaMapper = categoriaMapper;
    }

    public TipoPedidoEntity toEntity(TipoPedido tipoPedido) {
        TipoPedidoEntity entity = new TipoPedidoEntity();

        if(tipoPedido.getId()!=null){
            entity.setId(tipoPedido.getId());
        }
        entity.setCodigo(tipoPedido.getCodigo());
        entity.setNome(tipoPedido.getNome());
        entity.setDescricao(tipoPedido.getDescricao());
        entity.setPrazoEstimado(tipoPedido.getPrazoEstimado());
        entity.setValorBase(tipoPedido.getValorBase());
        entity.setRequerVistoria(tipoPedido.isRequerVistoria());
        entity.setRequerAnaliseTecnica(tipoPedido.isRequerAnaliseTecnica());
        entity.setRequerAprovacao(tipoPedido.isRequerAprovacao());
        entity.setDisponivelPortal(tipoPedido.isDisponivelPortal());
        entity.setAtivo(tipoPedido.isAtivo());

        CategoriaPedidoEntity categoria = categoriaMapper.toEntity(tipoPedido.getCategoria());
        entity.setCategoriaId(categoria);

        return entity;
    }

    public TipoPedido toDomain(TipoPedidoEntity entity) {
        return new TipoPedido(
                entity.getId(),
                entity.getCodigo(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getPrazoEstimado(),
                entity.getValorBase(),
                entity.isRequerVistoria(),
                entity.isRequerAnaliseTecnica(),
                entity.isRequerAprovacao(),
                entity.isDisponivelPortal(),
                entity.isAtivo(),
                categoriaMapper.toDomain(entity.getCategoriaId()),
                null
        );
    }
}

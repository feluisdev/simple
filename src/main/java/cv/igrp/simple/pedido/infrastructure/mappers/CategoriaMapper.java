package cv.igrp.simple.pedido.infrastructure.mappers;

import cv.igrp.simple.pedido.domain.models.CategoriaPedido;
import cv.igrp.simple.pedido.infrastructure.persistence.entity.CategoriaPedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaPedidoEntity toEntity(CategoriaPedido categoria) {
        if (categoria == null) return null;

        CategoriaPedidoEntity entity = new CategoriaPedidoEntity();
        entity.setId(categoria.getId());
        entity.setNome(categoria.getNome());
        entity.setDescricao(categoria.getDescricao());
        entity.setIcone(categoria.getIcone());
        entity.setCor(categoria.getCor());
        entity.setOrdem(categoria.getOrdem());
        entity.setAtivo(categoria.isAtivo());

        return entity;
    }

    public CategoriaPedido toDomain(CategoriaPedidoEntity entity) {
        if (entity == null) return null;

        return new CategoriaPedido(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getIcone(),
                entity.getCor(),
                entity.getOrdem(),
                entity.isAtivo()
        );
    }
}

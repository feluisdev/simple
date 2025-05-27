package cv.igrp.simple.pedido.infrastructure.persistence.repository;

import cv.igrp.simple.pedido.domain.models.CategoriaPedido;
import cv.igrp.simple.pedido.domain.repository.CategoriaPedidoRepository;
import cv.igrp.simple.pedido.infrastructure.mappers.CategoriaMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaPedidoRepositoryImpl implements CategoriaPedidoRepository {

    private final CategoriaPedidoEntityRepository jpaCategoriaPedidoEntityRepository;

    private final CategoriaMapper categoriaMapper;

    public CategoriaPedidoRepositoryImpl(CategoriaPedidoEntityRepository jpaCategoriaPedidoEntityRepository, CategoriaMapper categoriaMapper) {
        this.jpaCategoriaPedidoEntityRepository = jpaCategoriaPedidoEntityRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public void save(CategoriaPedido categoria) {

        var entity = categoriaMapper.toEntity(categoria);
        jpaCategoriaPedidoEntityRepository.save(entity);
    }

    @Override
    public CategoriaPedido getById(Integer categoriaId) {
        var entity = jpaCategoriaPedidoEntityRepository.findById(categoriaId)
                .orElseThrow();

        return categoriaMapper.toDomain(entity);
    }
}

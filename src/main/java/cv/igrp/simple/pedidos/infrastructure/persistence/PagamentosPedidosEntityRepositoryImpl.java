package cv.igrp.simple.pedidos.infrastructure.persistence;

import cv.igrp.simple.pedidos.domain.models.PagamentosPedidosEntity;
import cv.igrp.simple.pedidos.domain.repository.IPagamentosPedidosEntityRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PagamentosPedidosEntityRepositoryImpl implements IPagamentosPedidosEntityRepository {

    private final PagamentosPedidosEntityRepository repository;

    public PagamentosPedidosEntityRepositoryImpl(@Lazy PagamentosPedidosEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public PagamentosPedidosEntity save(PagamentosPedidosEntity pagamentospedidosentity) {
        return repository.save(pagamentospedidosentity);
    }

    @Override
    public Optional<PagamentosPedidosEntity> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<PagamentosPedidosEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}
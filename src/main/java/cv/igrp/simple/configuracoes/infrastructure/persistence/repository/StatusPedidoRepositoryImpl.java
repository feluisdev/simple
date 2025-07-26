package cv.igrp.simple.configuracoes.infrastructure.persistence.repository;

import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.shared.infrastructure.persistence.repository.StatusPedidoEntityRepository;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StatusPedidoRepositoryImpl implements StatusPedidoRepository {

    private final StatusPedidoEntityRepository jpaStatusPedidoEntityRepository;

    private final StatusPedidoMapper statusPedidoMapper;

    @Override
    public StatusPedido save(StatusPedido statusPedido) {

        var entity = statusPedidoMapper.toEntity(statusPedido);

        return statusPedidoMapper.toDomain(jpaStatusPedidoEntityRepository.save(entity));

    }

    @Override
    public Optional<StatusPedido> getById(Identificador statusPedidoUuid) {
       return  jpaStatusPedidoEntityRepository
                .findByStatusUuid(statusPedidoUuid.getValor()).map(statusPedidoMapper::toDomain);

    }

    @Override
    public Optional<StatusPedido> getByCodigo(String codigo) {
        return jpaStatusPedidoEntityRepository.findByCodigo(codigo)
                .map(statusPedidoMapper::toDomain);
    }

    @Override
    public boolean existByCodigo(String codigo) {
        return jpaStatusPedidoEntityRepository.existsByCodigo(codigo) ;
    }

    @Override
    public List<StatusPedido> getAll() {
        return jpaStatusPedidoEntityRepository.findAll().stream()
                .map(statusPedidoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<StatusPedido> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<StatusPedido> getByAtivo(boolean ativo) {
        return jpaStatusPedidoEntityRepository.findByAtivoTrue().stream()
                .map(statusPedidoMapper::toDomain)
                .toList();
    }
}

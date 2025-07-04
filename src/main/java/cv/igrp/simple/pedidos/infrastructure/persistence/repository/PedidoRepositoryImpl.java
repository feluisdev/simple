package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.pedidos.domain.filter.PedidoFilter;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.infrastructure.persistence.entity.PedidoEntity;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.PedidoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoEntityRepository pedidoEntityRepository;
    private final PedidoMapper pedidoMapper;

    @Override
    public Pedido save(Pedido pedido) {
        var entityPedido = pedidoMapper.toEntity(pedido);

        return pedidoMapper.toDomain(pedidoEntityRepository.save(entityPedido));
    }

    @Override
    public Optional<Pedido> findById(Identificador pedidoUuid) {
        return pedidoEntityRepository.findByPedidoUuid(pedidoUuid.getValor())
                .map(pedidoMapper::toDomain);
    }

    @Override
    public Optional<Pedido> findByCodigoAcompanhamento(CodigoAcompanhamento codigoAcompanhamento) {
        return pedidoEntityRepository
                .findByCodigoAcompanhamento(codigoAcompanhamento.getValor())
                .map(pedidoMapper::toDomain);
    }

    @Override
    public List<Pedido> findAll(PedidoFilter filter) {

        var pageable = PageRequest.of(filter.getPageNumber(), filter.getPageSize());

        Specification<PedidoEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getTipoServicoId() != null) {
                predicates = cb.and(predicates, cb.equal(
                        root.get("tipoServicoId").get("tipoServicoUuid"),
                        filter.getTipoServicoId().getValor()
                ));
            }

            if (filter.getCodigoAcompanhamento() != null) {
                predicates = cb.and(predicates, cb.equal(root.get("codigoAcompanhamento"), filter.getCodigoAcompanhamento().getValor()));
            }

            if (filter.getUtenteId() != null) {
                predicates = cb.and(predicates, cb.equal(root.get("utenteId").get("id"), filter.getUtenteId()));
            }

            if (filter.getUtenteNome() != null && !filter.getUtenteNome().isBlank()) {
                predicates = cb.and(predicates, cb.like(
                        cb.lower(root.get("utenteId").get("nome")),
                        "%" + filter.getUtenteNome().trim().toLowerCase() + "%"
                ));
            }

            if (filter.getUtenteNumero() != null && !filter.getUtenteNumero().isBlank()) {
                predicates = cb.and(predicates, cb.equal(
                        root.get("utenteId").get("numero"),
                        filter.getUtenteNumero()
                ));
            }

            if (filter.getDataDe() != null) {
                predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("dataInicio"), filter.getDataDe()));
            }
            if (filter.getDataAte() != null) {
                predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("dataInicio"), filter.getDataAte()));
            }

            return predicates;
        };

        var page = pedidoEntityRepository.findAll(spec, pageable);

        // Converte usando seu toDomain direto:
        return page.stream()
                .map(pedidoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pedido> findAllByUtenteId(Integer utenteID) {
        var lista = pedidoEntityRepository.findAllByUtenteId_Id(utenteID);
        return lista.stream()
                .map(pedidoMapper::toDomain).collect(Collectors.toList());
    }
}

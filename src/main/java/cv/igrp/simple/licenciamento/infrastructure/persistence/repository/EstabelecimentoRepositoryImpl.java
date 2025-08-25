package cv.igrp.simple.licenciamento.infrastructure.persistence.repository;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter.EstabelecimentoFilter;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models.Estabelecimento;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.EstabelecimentoRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.EstabelecimentoMapper;
import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.EstabelecimentoEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.EstabelecimentoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EstabelecimentoRepositoryImpl implements EstabelecimentoRepository {

    private final EstabelecimentoEntityRepository estabelecimentoEntityRepository;
    private final EstabelecimentoMapper estabelecimentoMapper;

    @Transactional
    @Override
    public Estabelecimento save(Estabelecimento estabelecimento) {
        var entity = estabelecimentoMapper.toEntity(estabelecimento);
        var saved = estabelecimentoEntityRepository.save(entity);
        return estabelecimentoMapper.toDomain(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Estabelecimento> findById(Identificador idEstabelecimento) {
        return estabelecimentoEntityRepository.findByExternalId(idEstabelecimento.getValor())
                .map(estabelecimentoMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Estabelecimento> findAll() {
        List<EstabelecimentoEntity> entities = estabelecimentoEntityRepository.findAll();
        return entities.stream()
                .map(estabelecimentoMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Estabelecimento> findAll(EstabelecimentoFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber() != null ? filter.getPageNumber() : 0,
                filter.getPageSize() != null ? filter.getPageSize() : 20
        );

        Specification<EstabelecimentoEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();


            if (filter.getNome() != null && !filter.getNome().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(cb.lower(root.get("nome")), filter.getNome().trim().toLowerCase()));
            }

            if (filter.getGerente() != null && !filter.getGerente().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(cb.lower(root.get("gerente")), filter.getGerente().trim().toLowerCase()));
            }

            if (filter.getEstado() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("estado"), filter.getEstado()));
            } else {
                predicates = cb.and(predicates,
                        cb.equal(root.get("estado"), Estado.ATIVO));
            }

            return predicates;
        };

        var page = estabelecimentoEntityRepository.findAll(spec, pageable);
        return page.stream()
                .map(estabelecimentoMapper::toDomain)
                .toList();
    }
}

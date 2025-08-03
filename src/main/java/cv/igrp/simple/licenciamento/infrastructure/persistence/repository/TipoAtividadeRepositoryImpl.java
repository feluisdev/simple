package cv.igrp.simple.licenciamento.infrastructure.persistence.repository;


import cv.igrp.simple.licenciamento.domain.filter.TipoAtividadeFilter;
import cv.igrp.simple.licenciamento.domain.models.TipoAtividade;
import cv.igrp.simple.licenciamento.domain.repository.TipoAtividadeRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.TipoAtividadeMapper;
import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.TipoAtividadeEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.TipoAtividadeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TipoAtividadeRepositoryImpl implements TipoAtividadeRepository {

    private final TipoAtividadeEntityRepository tipoAtividadeEntityRepository;
    private final TipoAtividadeMapper tipoAtividadeMapper;

    @Transactional
    @Override
    public TipoAtividade save(TipoAtividade tipoAtividade) {
        var entity = tipoAtividadeMapper.toEntity(tipoAtividade);
        var saved = tipoAtividadeEntityRepository.save(entity);
        return tipoAtividadeMapper.toDomain(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<TipoAtividade> findById(Identificador idTipoAtividade) {
        return tipoAtividadeEntityRepository
                .findByExternalId(idTipoAtividade.getValor())
                .map(tipoAtividadeMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoAtividade> findAll() {
        List<TipoAtividadeEntity> entities = tipoAtividadeEntityRepository.findAll();
        return entities.stream()
                .map(tipoAtividadeMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<TipoAtividade> findAll(TipoAtividadeFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber() != null ? filter.getPageNumber() : 0,
                filter.getPageSize() != null ? filter.getPageSize() : 20
        );

        Specification<TipoAtividadeEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getCodigo() != null && !filter.getCodigo().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("codigo")), "%" + filter.getCodigo().trim().toLowerCase() + "%"));
            }

            if (filter.getDescricao() != null && !filter.getDescricao().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("descricao")), "%" + filter.getDescricao().trim().toLowerCase() + "%"));
            }

            if (filter.getEstado() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("estado"), filter.getEstado()));
            } else {
                // Por padrão busca só ativos
                predicates = cb.and(predicates,
                        cb.equal(root.get("estado"), Estado.ATIVO));
            }

            return predicates;
        };

        var page = tipoAtividadeEntityRepository.findAll(spec, pageable);
        return page.stream()
                .map(tipoAtividadeMapper::toDomain)
                .toList();
    }
}

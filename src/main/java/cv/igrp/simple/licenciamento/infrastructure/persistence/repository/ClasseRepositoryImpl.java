package cv.igrp.simple.licenciamento.infrastructure.persistence.repository;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter.ClasseFilter;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models.Classe;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.ClasseRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.ClasseMapper;
import cv.igrp.simple.shared.application.constants.Estado;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.ClasseEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.ClasseEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ClasseRepositoryImpl implements ClasseRepository {

    private final ClasseEntityRepository classeEntityRepository;
    private final ClasseMapper classeMapper;

    @Transactional
    @Override
    public Classe save(Classe classe) {
        var entity = classeMapper.toEntity(classe);
        var saved = classeEntityRepository.save(entity);
        return classeMapper.toDomain(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Classe> findById(Identificador idClasse) {
        return classeEntityRepository.findByExternalId(idClasse.getValor())
                .map(classeMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Classe> findAll() {
        List<ClasseEntity> entities = classeEntityRepository.findAll();
        return entities.stream()
                .map(classeMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Classe> findAll(ClasseFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber() != null ? filter.getPageNumber() : 0,
                filter.getPageSize() != null ? filter.getPageSize() : 20
        );

        Specification<ClasseEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getClasse() != null && !filter.getClasse().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("classe"), filter.getClasse().trim()));
            }

            if (filter.getDescricao() != null && !filter.getDescricao().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("descricao")), "%" + filter.getDescricao().trim().toLowerCase() + "%"));
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

        var page = classeEntityRepository.findAll(spec, pageable);
        return page.stream()
                .map(classeMapper::toDomain)
                .toList();
    }
}

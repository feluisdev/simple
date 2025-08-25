package cv.igrp.simple.licenciamento.infrastructure.persistence.repository;

import cv.igrp.simple.licenciamento.domain.license2.filter.CategoryFilter;
import cv.igrp.simple.licenciamento.domain.license2.models.Category;
import cv.igrp.simple.licenciamento.domain.license2.repository.CategoryRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.CategoryMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.CategoryEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.CategoryEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryEntityRepository categoryEntityRepository;
    private final CategoryMapper categoryMapper;

    @Override
    @Transactional
    public Category save(Category category) {
        CategoryEntity entity = categoryMapper.toEntity(category);
        return categoryMapper.toDomain(categoryEntityRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findById(Identificador id) {
        return categoryEntityRepository.findById(id.getValor())
                .map(categoryMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return categoryEntityRepository.findAllByActiveTrue().stream()
                .map(categoryMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll(CategoryFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber() != null ? filter.getPageNumber() : 0,
                filter.getPageSize() != null ? filter.getPageSize() : 20
        );

        Specification<CategoryEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("name")), "%" + filter.getName().trim().toLowerCase() + "%"));
            }

            if (filter.getCode() != null && !filter.getCode().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("code"), filter.getCode().trim()));
            }

            if (filter.getSectorId() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("sectorId").get("id"), filter.getSectorId().getValor()));
            }

            if (filter.getParentId() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("parentId").get("id"), filter.getParentId().getValor()));
            }

            if (filter.getLevel() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("level"), filter.getLevel()));
            }

            // Ativo por padrão: true. Só mostra inativos se filter.isActive() for false
            boolean activeFilter = filter.isActive();
            predicates = cb.and(predicates, cb.equal(root.get("active"), activeFilter));

            return predicates;
        };

        var page = categoryEntityRepository.findAll(spec, pageable);
        return page.stream()
                .map(categoryMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Identificador id) {
        throw new UnsupportedOperationException("Delete não implementado ainda");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return categoryEntityRepository.existsByCode(code);
    }
}

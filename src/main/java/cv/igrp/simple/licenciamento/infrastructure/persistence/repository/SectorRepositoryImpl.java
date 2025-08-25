package cv.igrp.simple.licenciamento.infrastructure.persistence.repository;

import cv.igrp.simple.licenciamento.domain.license2.filter.SectorFilter;
import cv.igrp.simple.licenciamento.domain.license2.models.Sector;
import cv.igrp.simple.licenciamento.domain.license2.repository.SectorRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.SectorMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.SectorEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.SectorEntityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class SectorRepositoryImpl implements SectorRepository {

    private final SectorEntityRepository sectorEntityRepository;
    private final SectorMapper sectorMapper;

    public SectorRepositoryImpl(SectorEntityRepository sectorEntityRepository, SectorMapper sectorMapper) {
        this.sectorEntityRepository = sectorEntityRepository;
        this.sectorMapper = sectorMapper;
    }

    @Override
    @Transactional
    public Sector save(Sector sector) {
        SectorEntity entity = sectorMapper.toEntity(sector);
        return sectorMapper.toDomain(sectorEntityRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sector> findById(Identificador id) {
        return sectorEntityRepository.findById(id.getValor())
                .map(sectorMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sector> findAll() {
        return sectorEntityRepository.findAllByActiveTrue().stream()
                .map(sectorMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sector> findAll(SectorFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber() != null ? filter.getPageNumber() : 0,
                filter.getPageSize() != null ? filter.getPageSize() : 20
        );

        Specification<SectorEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("name")), "%" + filter.getName().trim().toLowerCase() + "%"));
            }

            if (filter.getCode() != null && !filter.getCode().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("code"), filter.getCode().trim()));
            }

            if (filter.getSectorType() != null && !filter.getSectorType().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("sectorTypeKey"), filter.getSectorType().trim()));
            }

            boolean activeFilter = filter.isActive();
            predicates = cb.and(predicates, cb.equal(root.get("active"), activeFilter));

            return predicates;
        };

        var page = sectorEntityRepository.findAll(spec, pageable);
        return page.stream()
                .map(sectorMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(Identificador id) {

        throw new UnsupportedOperationException("Delete não implementado ainda");

    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return sectorEntityRepository.existsByCode(code);
    }
}

package cv.igrp.simple.licenciamento.infrastructure.persistence.repository;


import cv.igrp.simple.licenciamento.domain.filter.LicencaComercialFilter;
import cv.igrp.simple.licenciamento.domain.models.LicencaComercial;
import cv.igrp.simple.licenciamento.domain.repository.LicencaComercialRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.LicencaComercialMapper;
import cv.igrp.simple.shared.application.constants.EstadoLicenca;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.LicencaComercialEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.LicencaComercialEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LicencaComercialRepositoryImpl implements LicencaComercialRepository {

    private final LicencaComercialEntityRepository licencaEntityRepository;
    private final LicencaComercialMapper licencaMapper;

    @Transactional
    @Override
    public LicencaComercial save(LicencaComercial licenca) {
        var entity = licencaMapper.toEntity(licenca);
        var saved = licencaEntityRepository.save(entity);
        return licencaMapper.toDomain(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<LicencaComercial> findById(Identificador idLicenca) {
        return licencaEntityRepository.findByExternalId(idLicenca.getValor())
                .map(licencaMapper::toDomain);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LicencaComercial> findAll() {
        List<LicencaComercialEntity> entities = licencaEntityRepository.findAll();
        return entities.stream()
                .map(licencaMapper::toDomain)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<LicencaComercial> findAll(LicencaComercialFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber() != null ? filter.getPageNumber() : 0,
                filter.getPageSize() != null ? filter.getPageSize() : 20
        );

        Specification<LicencaComercialEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getAlvara() != null && !filter.getAlvara().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("alvara"), filter.getAlvara()));
            }

            if (filter.getIdEstabalecimento() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("idEstabelecimento").get("externalId"), filter.getIdEstabalecimento().getValor()));
            }

            if (filter.getEstadoLicenca() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("estado"), filter.getEstadoLicenca()));
            } else {
                predicates = cb.and(predicates,
                        cb.equal(root.get("estado"), EstadoLicenca.A));
            }

            return predicates;
        };

        var page = licencaEntityRepository.findAll(spec, pageable);
        return page.stream()
                .map(licencaMapper::toDomain)
                .toList();
    }
}

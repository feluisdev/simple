package cv.igrp.simple.licenciamento.domain.license2.repository;

import cv.igrp.simple.licenciamento.domain.license2.filter.LicenseTypeFilter;
import cv.igrp.simple.licenciamento.domain.license2.models.LicenseType;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface LicenseTypeRepository {

    LicenseType save(LicenseType licenseType);

    Optional<LicenseType> findById(Identificador id);

    List<LicenseType> findAll();

    List<LicenseType> findAll(LicenseTypeFilter filter);

    void delete(Identificador id);

    boolean existsByCode(String code);
}

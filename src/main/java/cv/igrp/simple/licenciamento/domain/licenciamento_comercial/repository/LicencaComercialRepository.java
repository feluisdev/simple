package cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter.LicencaComercialFilter;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models.LicencaComercial;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface LicencaComercialRepository {

    LicencaComercial save(LicencaComercial licenca);

    Optional<LicencaComercial> findById(Identificador idLicenca);

    List<LicencaComercial> findAll();

    List<LicencaComercial> findAll(LicencaComercialFilter filter);
}

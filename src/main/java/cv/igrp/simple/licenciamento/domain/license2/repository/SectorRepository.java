package cv.igrp.simple.licenciamento.domain.license2.repository;

import cv.igrp.simple.licenciamento.domain.license2.filter.CategoryFilter;
import cv.igrp.simple.licenciamento.domain.license2.filter.SectorFilter;
import cv.igrp.simple.licenciamento.domain.license2.models.Sector;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface SectorRepository {

    Sector save(Sector sector);

    Optional<Sector> findById(Identificador id);

    List<Sector> findAll();

    List<Sector> findAll(SectorFilter filter);

    void delete(Identificador id);

    boolean existsByCode(String code);
}

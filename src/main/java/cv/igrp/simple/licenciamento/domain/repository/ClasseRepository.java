package cv.igrp.simple.licenciamento.domain.repository;

import cv.igrp.simple.licenciamento.domain.filter.ClasseFilter;
import cv.igrp.simple.licenciamento.domain.models.Classe;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface ClasseRepository {

    Classe save(Classe classe);

    Optional<Classe> findById(Identificador idClasse);

    List<Classe> findAll();

    List<Classe> findAll(ClasseFilter filter);
}

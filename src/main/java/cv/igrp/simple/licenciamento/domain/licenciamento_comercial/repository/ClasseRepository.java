package cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter.ClasseFilter;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models.Classe;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface ClasseRepository {

    Classe save(Classe classe);

    Optional<Classe> findById(Identificador idClasse);

    List<Classe> findAll();

    List<Classe> findAll(ClasseFilter filter);
}

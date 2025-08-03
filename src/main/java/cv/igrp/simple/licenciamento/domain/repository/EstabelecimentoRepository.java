package cv.igrp.simple.licenciamento.domain.repository;

import cv.igrp.simple.licenciamento.domain.filter.EstabelecimentoFilter;
import cv.igrp.simple.licenciamento.domain.models.Estabelecimento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface EstabelecimentoRepository {

    Estabelecimento save(Estabelecimento estabelecimento);

    Optional<Estabelecimento> findById(Identificador idEstabelecimento);

    List<Estabelecimento> findAll();

    List<Estabelecimento> findAll(EstabelecimentoFilter filter);
}

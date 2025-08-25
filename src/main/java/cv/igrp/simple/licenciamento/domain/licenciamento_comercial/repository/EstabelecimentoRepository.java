package cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter.EstabelecimentoFilter;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models.Estabelecimento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface EstabelecimentoRepository {

    Estabelecimento save(Estabelecimento estabelecimento);

    Optional<Estabelecimento> findById(Identificador idEstabelecimento);

    List<Estabelecimento> findAll();

    List<Estabelecimento> findAll(EstabelecimentoFilter filter);
}

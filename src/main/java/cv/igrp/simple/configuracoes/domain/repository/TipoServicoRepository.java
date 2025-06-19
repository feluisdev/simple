package cv.igrp.simple.configuracoes.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.TipoServico;

import java.util.Optional;

public interface TipoServicoRepository {

    TipoServico save(TipoServico tipoServico);

    Optional<TipoServico> findById(Integer id);
}

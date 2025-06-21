package cv.igrp.simple.configuracoes.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServicoFilter;

import java.util.List;
import java.util.Optional;

public interface TipoServicoRepository {

    TipoServico save(TipoServico tipoServico);

    Optional<TipoServico> findById(Integer id);

    Optional<TipoServico> findByCodigo(String codigo);

    List<TipoServico> getAll(TipoServicoFilter filter);
}

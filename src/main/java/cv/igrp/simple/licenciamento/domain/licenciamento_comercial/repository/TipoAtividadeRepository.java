package cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.filter.TipoAtividadeFilter;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models.TipoAtividade;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface TipoAtividadeRepository {

    TipoAtividade save(TipoAtividade tipoAtividade);

    Optional<TipoAtividade> findById(Identificador idTipoAtividade);

    List<TipoAtividade> findAll();

    List<TipoAtividade> findAll(TipoAtividadeFilter filter);
}

package cv.igrp.simple.configuracoes.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.Combo;

import java.util.List;

public interface TipoServicoReadRepository {

    List<Combo<String>> getTipoServicoAtivos();
}

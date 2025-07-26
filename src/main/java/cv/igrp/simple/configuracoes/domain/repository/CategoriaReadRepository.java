package cv.igrp.simple.configuracoes.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.Combo;

import java.util.List;

public interface CategoriaReadRepository {

    List<Combo<String>> getCategoriasAtivas();
}

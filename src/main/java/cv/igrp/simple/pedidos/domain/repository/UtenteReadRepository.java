package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.Combo;

import java.util.List;

public interface UtenteReadRepository {

    List<Combo<Integer>> getUtenteAtivos();
}

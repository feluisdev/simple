package cv.igrp.simple.utente.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.Combo;

import java.util.List;

public interface UtenteReadRepository {

    List<Combo<Integer>> getUtenteAtivos();
}

package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.Utente;
import cv.igrp.simple.shared.domain.valueobject.Identificador;

import java.util.Optional;

public interface UtenteRepository {
    Optional<Utente> findById(Identificador utenteUuid);

    Optional<Utente> findById(Integer id);
}

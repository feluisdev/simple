package cv.igrp.simple.utente.domain.service;

import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    private final UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public String geraNumeroUtente(){

        Integer ultimoId = utenteRepository.findMaxId().orElse(0);
        Integer novoId = ultimoId + 1;
        return "N" + novoId;
    }

    public Utente obterUtentePorId(int idUtente) {

        Optional<Utente> utente = utenteRepository.findById(idUtente);

        return utente.orElseThrow(() -> new IgrpResponseStatusException(new IgrpProblem<>(
                HttpStatus.NOT_FOUND,
                "Utente não encontrado com o ID: " + idUtente,
                null)));
    }
}

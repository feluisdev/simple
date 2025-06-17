package cv.igrp.simple.utente.domain.service;

import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.utente.domain.models.UtenteEntity;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtenteService {

    private final UtenteEntityRepository utenteRepository;

    public UtenteService(UtenteEntityRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public String geraNumeroUtente(){

        Integer ultimoId = utenteRepository.findMaxId().orElse(0);
        Integer novoId = ultimoId + 1;
        return "N" + novoId;
    }

    public UtenteEntity obterUtentePorId(int idUtente) {

        Optional<UtenteEntity> utente = utenteRepository.findById(idUtente);

        return utente.orElseThrow(() -> new IgrpResponseStatusException(new IgrpProblem<>(
                HttpStatus.NOT_FOUND,
                "Utente não encontrado com o ID: " + idUtente,
                null)));
    }
}

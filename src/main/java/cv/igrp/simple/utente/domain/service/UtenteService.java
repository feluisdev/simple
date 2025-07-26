package cv.igrp.simple.utente.domain.service;

import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteEntityRepository;
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

        //todo, handle logic to generate numero utente
        Integer ultimoId = utenteRepository.findMaxId().orElse(0);
        Integer novoId = ultimoId + 1;
        return "N" + novoId;
    }

    public UtenteEntity obterUtentePorId(int idUtente) {

        Optional<UtenteEntity> utente = utenteRepository.findById(idUtente);

        return utente.orElseThrow(() -> IgrpResponseStatusException.notFound(
                "Utente não encontrado com o ID: " + idUtente
        ));
    }
}

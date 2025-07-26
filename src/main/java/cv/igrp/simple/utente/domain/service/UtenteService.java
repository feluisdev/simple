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
        // Generate unique user number based on UUID
        String uuid = UUID.randomUUID().toString();
        // Take first 8 characters of UUID and convert to uppercase
        String uniqueId = uuid.substring(0, 8).toUpperCase();
        // Add prefix 'N' to the unique ID
        return "N" + uniqueId;
    }

    public UtenteEntity obterUtentePorId(int idUtente) {

        Optional<UtenteEntity> utente = utenteRepository.findById(idUtente);

        return utente.orElseThrow(() -> IgrpResponseStatusException.notFound(
                "Utente não encontrado com o ID: " + idUtente
        ));
    }
}

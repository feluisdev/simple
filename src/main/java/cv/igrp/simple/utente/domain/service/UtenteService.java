package cv.igrp.simple.utente.domain.service;

import com.github.f4b6a3.uuid.UuidCreator;
import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    private final UtenteEntityRepository utenteRepository;

    public UtenteService(UtenteEntityRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    /**
     * Generates a short, unique user identifier with a 'N' prefix.
     * Uses time-ordered UUIDs for better performance and temporal sorting.
     *
     */
    public String geraNumeroUtente() {
        // Generate a time-ordered UUID
        String uuid = UuidCreator.getTimeOrdered().toString();

        // Remove dashes and take the first 8 characters, in uppercase
        String uniqueId = uuid.replace("-", "").substring(0, 8).toUpperCase();

        // Prefix with 'N' to identify the user
        return "N" + uniqueId;
    }

    public UtenteEntity obterUtentePorId(int idUtente) {

        Optional<UtenteEntity> utente = utenteRepository.findById(idUtente);

        return utente.orElseThrow(() -> IgrpResponseStatusException.notFound(
                "Utente não encontrado com o ID: " + idUtente
        ));
    }
}

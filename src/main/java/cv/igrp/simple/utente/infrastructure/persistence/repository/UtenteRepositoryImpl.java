package cv.igrp.simple.utente.infrastructure.persistence.repository;

import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.repository.UtenteRepository;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteEntityRepository;
import cv.igrp.simple.utente.infrastructure.mappers.UtenteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UtenteRepositoryImpl implements UtenteRepository {

    private final UtenteEntityRepository utenteEntityRepository;
    private final UtenteMapper utenteMapper;

    @Override
    public Optional<Utente> findById(Identificador utenteUuid) {
        return Optional.empty();
    }

    @Override
    public Optional<Utente> findById(Integer id) {
        return utenteEntityRepository.findById(id).map(utenteMapper::toDomain);
    }
}

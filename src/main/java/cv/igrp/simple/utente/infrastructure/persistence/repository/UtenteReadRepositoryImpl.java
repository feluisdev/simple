package cv.igrp.simple.utente.infrastructure.persistence.repository;

import cv.igrp.simple.configuracoes.domain.models.Combo;
import cv.igrp.simple.utente.domain.repository.UtenteReadRepository;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteEntityRepository;
import cv.igrp.simple.utente.application.constants.Estado;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UtenteReadRepositoryImpl implements UtenteReadRepository {

    private final UtenteEntityRepository utenteEntityRepository;

    public UtenteReadRepositoryImpl(UtenteEntityRepository utenteEntityRepository) {
        this.utenteEntityRepository = utenteEntityRepository;
    }

    @Override
    public List<Combo<Integer>> getUtenteAtivos() {
        return utenteEntityRepository.findByEstado(Estado.ATIVO)
                .stream()
                .map(utente -> new Combo<>(utente.getNome(), utente.getId()))
                .toList();
    }
}

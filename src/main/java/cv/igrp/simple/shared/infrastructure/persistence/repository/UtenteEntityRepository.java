package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface UtenteEntityRepository extends
    JpaRepository<UtenteEntity, Integer>,
    JpaSpecificationExecutor<UtenteEntity>
{

    /**
     * Busca o maior ID de utente no banco de dados.
     * Retorna null se não houver utentes.
     */
    @Query("SELECT MAX(u.id) FROM UtenteEntity u")
    Optional<Integer> findMaxId();
}
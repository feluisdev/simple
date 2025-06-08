package cv.igrp.simple.utente.infrastructure.persistence;

import cv.igrp.simple.utente.domain.models.Utente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface UtenteRepository extends
    JpaRepository<Utente, Integer>,
    JpaSpecificationExecutor<Utente>
{

    /**
     * Busca o maior ID de utente no banco de dados.
     * Retorna null se não houver utentes.
     */
    @Query("SELECT MAX(u.id) FROM Utente u")
    Optional<Integer> findMaxId();

}
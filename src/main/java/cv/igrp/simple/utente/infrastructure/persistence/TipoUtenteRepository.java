package cv.igrp.simple.utente.infrastructure.persistence;

import cv.igrp.simple.utente.domain.models.TipoUtente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface TipoUtenteRepository extends
    JpaRepository<TipoUtente, Integer>,
    JpaSpecificationExecutor<TipoUtente>
{

}
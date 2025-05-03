package cv.igrp.simple.utente.infrastructure.persistence;

import cv.igrp.simple.utente.domain.models.UtenteServico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface UtenteServicoRepository extends
    JpaRepository<UtenteServico, Integer>,
    JpaSpecificationExecutor<UtenteServico>
{
    Page<UtenteServico> findByUtenteId_Id(Integer utenteId, Pageable pageable);

    Optional<UtenteServico> findByIdAndUtenteId_Id(Integer servicoId, Integer utenteId);

    @Query("SELECT us FROM UtenteServico us WHERE us.id = :servicoId AND us.utenteId.id = :utenteId")
    Optional<UtenteServico> buscarPorServicoEUtente(@Param("servicoId") Integer servicoId, @Param("utenteId") Integer utenteId);

    @Query("SELECT us FROM UtenteServico us WHERE us.utenteId.id = :utenteId")
    Page<UtenteServico> buscarServicosPorUtenteId(@Param("utenteId") Integer utenteId, Pageable pageable);


}
package cv.igrp.simple.utente.domain.repository;

import cv.igrp.simple.utente.domain.models.UtenteServicoEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IUtenteServicoEntityRepository {

  /**
  * Save or update a UtenteServicoEntity.
  *
  * @param utenteservicoentity is the object to be saved
  * @return the UtenteServicoEntity object that was saved
  */
  UtenteServicoEntity save(UtenteServicoEntity utenteservicoentity);

  /**
  * Fetch a UtenteServicoEntity by its ID.
  *
  * @param id the UtenteServicoEntity's ID
  * @return an Optional UtenteServicoEntity, if found
  */
  Optional<UtenteServicoEntity> findById(Integer id);

  /**
  * Fetch all the UtenteServicoEntity objects.
  *
  * @return all UtenteServicoEntity objects
  */
  List<UtenteServicoEntity> findAll();

  /**
  * Deletes a UtenteServicoEntity by its ID
  *
  * @param id the UtenteServicoEntity's ID
  */
  void deleteById(Integer id);

}

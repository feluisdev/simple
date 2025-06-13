package cv.igrp.simple.utente.domain.repository;

import cv.igrp.simple.utente.domain.models.UtenteEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IUtenteEntityRepository {

  /**
  * Save or update a UtenteEntity.
  *
  * @param utenteentity is the object to be saved
  * @return the UtenteEntity object that was saved
  */
  UtenteEntity save(UtenteEntity utenteentity);

  /**
  * Fetch a UtenteEntity by its ID.
  *
  * @param id the UtenteEntity's ID
  * @return an Optional UtenteEntity, if found
  */
  Optional<UtenteEntity> findById(Integer id);

  /**
  * Fetch all the UtenteEntity objects.
  *
  * @return all UtenteEntity objects
  */
  List<UtenteEntity> findAll();

  /**
  * Deletes a UtenteEntity by its ID
  *
  * @param id the UtenteEntity's ID
  */
  void deleteById(Integer id);

}

package cv.igrp.simple.utente.domain.repository;

import cv.igrp.simple.utente.domain.models.TipoUtenteEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface ITipoUtenteEntityRepository {

  /**
  * Save or update a TipoUtenteEntity.
  *
  * @param tipoutenteentity is the object to be saved
  * @return the TipoUtenteEntity object that was saved
  */
  TipoUtenteEntity save(TipoUtenteEntity tipoutenteentity);

  /**
  * Fetch a TipoUtenteEntity by its ID.
  *
  * @param id the TipoUtenteEntity's ID
  * @return an Optional TipoUtenteEntity, if found
  */
  Optional<TipoUtenteEntity> findById(Integer id);

  /**
  * Fetch all the TipoUtenteEntity objects.
  *
  * @return all TipoUtenteEntity objects
  */
  List<TipoUtenteEntity> findAll();

  /**
  * Deletes a TipoUtenteEntity by its ID
  *
  * @param id the TipoUtenteEntity's ID
  */
  void deleteById(Integer id);

}

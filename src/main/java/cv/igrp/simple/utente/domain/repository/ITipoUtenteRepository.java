package cv.igrp.simple.utente.domain.repository;

import cv.igrp.simple.utente.domain.models.TipoUtente;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface ITipoUtenteRepository {

  /**
  * Save or update a TipoUtente.
  *
  * @param tipoutente is the object to be saved
  * @return the TipoUtente object that was saved
  */
  TipoUtente save(TipoUtente tipoutente);

  /**
  * Fetch a TipoUtente by its ID.
  *
  * @param id the TipoUtente's ID
  * @return an Optional TipoUtente, if found
  */
  Optional<TipoUtente> findById(Integer id);

  /**
  * Fetch all the TipoUtente objects.
  *
  * @return all TipoUtente objects
  */
  List<TipoUtente> findAll();

  /**
  * Deletes a TipoUtente by its ID
  *
  * @param id the TipoUtente's ID
  */
  void deleteById(Integer id);

}

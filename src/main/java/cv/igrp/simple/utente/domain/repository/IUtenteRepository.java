package cv.igrp.simple.utente.domain.repository;

import cv.igrp.simple.utente.domain.models.Utente;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IUtenteRepository {

  /**
  * Save or update a Utente.
  *
  * @param utente is the object to be saved
  * @return the Utente object that was saved
  */
  Utente save(Utente utente);

  /**
  * Fetch a Utente by its ID.
  *
  * @param id the Utente's ID
  * @return an Optional Utente, if found
  */
  Optional<Utente> findById(Integer id);

  /**
  * Fetch all the Utente objects.
  *
  * @return all Utente objects
  */
  List<Utente> findAll();

  /**
  * Deletes a Utente by its ID
  *
  * @param id the Utente's ID
  */
  void deleteById(Integer id);

}

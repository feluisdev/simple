package cv.igrp.simple.utente.domain.repository;

import cv.igrp.simple.utente.domain.models.UtenteServico;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IUtenteServicoRepository {

  /**
  * Save or update a UtenteServico.
  *
  * @param utenteservico is the object to be saved
  * @return the UtenteServico object that was saved
  */
  UtenteServico save(UtenteServico utenteservico);

  /**
  * Fetch a UtenteServico by its ID.
  *
  * @param id the UtenteServico's ID
  * @return an Optional UtenteServico, if found
  */
  Optional<UtenteServico> findById(Integer id);

  /**
  * Fetch all the UtenteServico objects.
  *
  * @return all UtenteServico objects
  */
  List<UtenteServico> findAll();

  /**
  * Deletes a UtenteServico by its ID
  *
  * @param id the UtenteServico's ID
  */
  void deleteById(Integer id);

}

package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.EtapasPedidosEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IEtapasPedidosEntityRepository {

  /**
  * Save or update a EtapasPedidosEntity.
  *
  * @param etapaspedidosentity is the object to be saved
  * @return the EtapasPedidosEntity object that was saved
  */
  EtapasPedidosEntity save(EtapasPedidosEntity etapaspedidosentity);

  /**
  * Fetch a EtapasPedidosEntity by its ID.
  *
  * @param id the EtapasPedidosEntity's ID
  * @return an Optional EtapasPedidosEntity, if found
  */
  Optional<EtapasPedidosEntity> findById(Integer id);

  /**
  * Fetch all the EtapasPedidosEntity objects.
  *
  * @return all EtapasPedidosEntity objects
  */
  List<EtapasPedidosEntity> findAll();

  /**
  * Deletes a EtapasPedidosEntity by its ID
  *
  * @param id the EtapasPedidosEntity's ID
  */
  void deleteById(Integer id);

}

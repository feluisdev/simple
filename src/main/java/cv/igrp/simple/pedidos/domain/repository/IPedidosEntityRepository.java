package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.PedidosEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IPedidosEntityRepository {

  /**
  * Save or update a PedidosEntity.
  *
  * @param pedidosentity is the object to be saved
  * @return the PedidosEntity object that was saved
  */
  PedidosEntity save(PedidosEntity pedidosentity);

  /**
  * Fetch a PedidosEntity by its ID.
  *
  * @param id the PedidosEntity's ID
  * @return an Optional PedidosEntity, if found
  */
  Optional<PedidosEntity> findById(Integer id);

  /**
  * Fetch all the PedidosEntity objects.
  *
  * @return all PedidosEntity objects
  */
  List<PedidosEntity> findAll();

  /**
  * Deletes a PedidosEntity by its ID
  *
  * @param id the PedidosEntity's ID
  */
  void deleteById(Integer id);

}

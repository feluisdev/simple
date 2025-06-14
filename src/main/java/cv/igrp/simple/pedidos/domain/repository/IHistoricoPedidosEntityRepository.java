package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.HistoricoPedidosEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IHistoricoPedidosEntityRepository {

  /**
  * Save or update a HistoricoPedidosEntity.
  *
  * @param historicopedidosentity is the object to be saved
  * @return the HistoricoPedidosEntity object that was saved
  */
  HistoricoPedidosEntity save(HistoricoPedidosEntity historicopedidosentity);

  /**
  * Fetch a HistoricoPedidosEntity by its ID.
  *
  * @param id the HistoricoPedidosEntity's ID
  * @return an Optional HistoricoPedidosEntity, if found
  */
  Optional<HistoricoPedidosEntity> findById(Integer id);

  /**
  * Fetch all the HistoricoPedidosEntity objects.
  *
  * @return all HistoricoPedidosEntity objects
  */
  List<HistoricoPedidosEntity> findAll();

  /**
  * Deletes a HistoricoPedidosEntity by its ID
  *
  * @param id the HistoricoPedidosEntity's ID
  */
  void deleteById(Integer id);

}

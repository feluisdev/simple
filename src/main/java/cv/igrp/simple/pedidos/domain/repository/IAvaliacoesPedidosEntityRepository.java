package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.AvaliacoesPedidosEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IAvaliacoesPedidosEntityRepository {

  /**
  * Save or update a AvaliacoesPedidosEntity.
  *
  * @param avaliacoespedidosentity is the object to be saved
  * @return the AvaliacoesPedidosEntity object that was saved
  */
  AvaliacoesPedidosEntity save(AvaliacoesPedidosEntity avaliacoespedidosentity);

  /**
  * Fetch a AvaliacoesPedidosEntity by its ID.
  *
  * @param id the AvaliacoesPedidosEntity's ID
  * @return an Optional AvaliacoesPedidosEntity, if found
  */
  Optional<AvaliacoesPedidosEntity> findById(Integer id);

  /**
  * Fetch a AvaliacoesPedidosEntity by pedidoId.
  *
  * @param pedidoId the pedido's ID
  * @return an Optional AvaliacoesPedidosEntity, if found
  */
  Optional<AvaliacoesPedidosEntity> findByPedidoId(Integer pedidoId);

  /**
  * Fetch all the AvaliacoesPedidosEntity objects.
  *
  * @return all AvaliacoesPedidosEntity objects
  */
  List<AvaliacoesPedidosEntity> findAll();

  /**
  * Deletes a AvaliacoesPedidosEntity by its ID
  *
  * @param id the AvaliacoesPedidosEntity's ID
  */
  void deleteById(Integer id);

}

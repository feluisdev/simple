package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.PagamentosPedidosEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IPagamentosPedidosEntityRepository {

  /**
  * Save or update a PagamentosPedidosEntity.
  *
  * @param pagamentospedidosentity is the object to be saved
  * @return the PagamentosPedidosEntity object that was saved
  */
  PagamentosPedidosEntity save(PagamentosPedidosEntity pagamentospedidosentity);

  /**
  * Fetch a PagamentosPedidosEntity by its ID.
  *
  * @param id the PagamentosPedidosEntity's ID
  * @return an Optional PagamentosPedidosEntity, if found
  */
  Optional<PagamentosPedidosEntity> findById(Integer id);

  /**
  * Fetch all the PagamentosPedidosEntity objects.
  *
  * @return all PagamentosPedidosEntity objects
  */
  List<PagamentosPedidosEntity> findAll();

  /**
  * Deletes a PagamentosPedidosEntity by its ID
  *
  * @param id the PagamentosPedidosEntity's ID
  */
  void deleteById(Integer id);

}

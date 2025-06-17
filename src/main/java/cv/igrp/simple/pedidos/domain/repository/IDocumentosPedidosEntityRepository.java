package cv.igrp.simple.pedidos.domain.repository;

import cv.igrp.simple.pedidos.domain.models.DocumentosPedidosEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface IDocumentosPedidosEntityRepository {

  /**
  * Save or update a DocumentosPedidosEntity.
  *
  * @param documentospedidosentity is the object to be saved
  * @return the DocumentosPedidosEntity object that was saved
  */
  DocumentosPedidosEntity save(DocumentosPedidosEntity documentospedidosentity);

  /**
  * Fetch a DocumentosPedidosEntity by its ID.
  *
  * @param id the DocumentosPedidosEntity's ID
  * @return an Optional DocumentosPedidosEntity, if found
  */
  Optional<DocumentosPedidosEntity> findById(Integer id);

  /**
  * Fetch all the DocumentosPedidosEntity objects.
  *
  * @return all DocumentosPedidosEntity objects
  */
  List<DocumentosPedidosEntity> findAll();

  /**
  * Deletes a DocumentosPedidosEntity by its ID
  *
  * @param id the DocumentosPedidosEntity's ID
  */
  void deleteById(Integer id);

}

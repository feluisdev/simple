package cv.igrp.simple.pedido.infrastructure.persistence.repository;

import cv.igrp.simple.pedido.infrastructure.persistence.entity.CategoriaPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface CategoriaPedidoEntityRepository extends
    JpaRepository<CategoriaPedidoEntity, Integer>,
    JpaSpecificationExecutor<CategoriaPedidoEntity>
{

}
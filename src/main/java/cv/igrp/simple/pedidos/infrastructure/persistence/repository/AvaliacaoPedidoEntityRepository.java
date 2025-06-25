package cv.igrp.simple.pedidos.infrastructure.persistence.repository;

import cv.igrp.simple.pedidos.infrastructure.persistence.entity.AvaliacaoPedidoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;



@Repository
public interface AvaliacaoPedidoEntityRepository extends
    JpaRepository<AvaliacaoPedidoEntity, Integer>,
    JpaSpecificationExecutor<AvaliacaoPedidoEntity>
{

}
package cv.igrp.simple.configuracoes.infrastructure.persistence;

import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.StatusPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusPedidoRepository extends 
    JpaRepository<StatusPedidoEntity, Integer>,
    JpaSpecificationExecutor<StatusPedidoEntity>
{
    /**
     * Verifica se existe um status de pedido com o código informado.
     */
    boolean existsByCodigo(String codigo);
    
    /**
     * Busca status de pedido por código.
     */
    Optional<StatusPedidoEntity> findByCodigo(String codigo);
    
    /**
     * Busca status de pedido visíveis no portal.
     */
    List<StatusPedidoEntity> findByVisivelPortalTrue();
    
    /**
     * Busca status de pedido ordenados por ordem.
     */
    List<StatusPedidoEntity> findAllByOrderByOrdemAsc();
    
    /**
     * Busca status de pedido visíveis no portal ordenados por ordem.
     */
    List<StatusPedidoEntity> findByVisivelPortalTrueOrderByOrdemAsc();
}
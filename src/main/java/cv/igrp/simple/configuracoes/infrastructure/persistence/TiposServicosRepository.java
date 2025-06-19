package cv.igrp.simple.configuracoes.infrastructure.persistence;

import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.TiposServicosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TiposServicosRepository extends 
    JpaRepository<TiposServicosEntity, Integer>,
    JpaSpecificationExecutor<TiposServicosEntity>
{    
    /**
     * Verifica se existe um tipo de serviço com o código informado.
     */
    boolean existsByCodigo(String codigo);

    /**
     * Busca tipo de serviço por código.
     */
    Optional<TiposServicosEntity> findByCodigo(String codigo);
    
    /**
     * Busca tipos de serviço por categoria.
     */
    List<TiposServicosEntity> findByCategoriaId(Integer categoriaId);
    
    /**
     * Busca tipos de serviço ativos por categoria.
     */
    List<TiposServicosEntity> findByCategoriaIdAndAtivoTrue(Integer categoriaId);
    
    /**
     * Busca tipos de serviço disponíveis no portal.
     */
    List<TiposServicosEntity> findByDisponivelPortalTrueAndAtivoTrue();
    
    /**
     * Busca tipos de serviço que requerem vistoria.
     */
    List<TiposServicosEntity> findByRequerVistoriaTrue();
    
    /**
     * Busca tipos de serviço que requerem análise técnica.
     */
    List<TiposServicosEntity> findByRequerAnaliseTecTrue();
    
    /**
     * Busca tipos de serviço que requerem aprovação.
     */
    List<TiposServicosEntity> findByRequerAprovacaoTrue();
}
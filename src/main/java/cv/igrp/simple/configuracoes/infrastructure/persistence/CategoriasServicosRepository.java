package cv.igrp.simple.configuracoes.infrastructure.persistence;

import cv.igrp.simple.configuracoes.domain.models.CategoriasServicosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriasServicosRepository extends 
    JpaRepository<CategoriasServicosEntity, Integer>,
    JpaSpecificationExecutor<CategoriasServicosEntity>
{
    /**
     * Busca categoria por nome.
     */
    Optional<CategoriasServicosEntity> findByNome(String nome);
    
    /**
     * Busca categorias ativas.
     */
    List<CategoriasServicosEntity> findByAtivoTrue();
    
    /**
     * Busca categorias ordenadas por ordem.
     */
    List<CategoriasServicosEntity> findAllByOrderByOrdemAsc();
    
    /**
     * Busca categorias ativas ordenadas por ordem.
     */
    List<CategoriasServicosEntity> findByAtivoTrueOrderByOrdemAsc();
}
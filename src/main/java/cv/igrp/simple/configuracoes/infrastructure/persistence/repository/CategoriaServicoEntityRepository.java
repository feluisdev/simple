package cv.igrp.simple.configuracoes.infrastructure.persistence.repository;

import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriaServicoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CategoriaServicoEntityRepository extends
    JpaRepository<CategoriaServicoEntity, Integer>,
    JpaSpecificationExecutor<CategoriaServicoEntity>
{


    Optional<CategoriaServicoEntity> findByCategoriaUuid(UUID categoriaUuid);

    Optional<CategoriaServicoEntity> findByCodigo(String codigo);

    /*Optional<CategoriaServicoEntity> findByNome(String nome);


    List<CategoriaServicoEntity> findByAtivoTrue();


    List<CategoriaServicoEntity> findAllByOrderByOrdemAsc();


    List<CategoriaServicoEntity> findByAtivoTrueOrderByOrdemAsc();*/

}
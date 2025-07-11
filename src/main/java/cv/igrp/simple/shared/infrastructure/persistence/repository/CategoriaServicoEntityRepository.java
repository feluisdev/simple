package cv.igrp.simple.shared.infrastructure.persistence.repository;

import cv.igrp.simple.shared.infrastructure.persistence.entity.CategoriaServicoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
@RepositoryRestResource(path = "categoriaServicoEntity")
public interface CategoriaServicoEntityRepository extends
    JpaRepository<CategoriaServicoEntity, Integer>,
    JpaSpecificationExecutor<CategoriaServicoEntity>
{

    Optional<CategoriaServicoEntity> findByCategoriaUuid(UUID categoriaUuid);

    Optional<CategoriaServicoEntity> findByCodigo(String codigo);

    @Query("SELECT c FROM CategoriaServicoEntity c LEFT JOIN FETCH c.tiposservicos WHERE c.categoriaUuid = :uuid")
    Optional<CategoriaServicoEntity> findWithTiposByCategoriaUuid(@Param("uuid") UUID uuid);

    List<CategoriaServicoEntity> findByEstadoTrue();

    /*Optional<CategoriaServicoEntity> findByNome(String nome);


    List<CategoriaServicoEntity> findByAtivoTrue();


    List<CategoriaServicoEntity> findAllByOrderByOrdemAsc();


    List<CategoriaServicoEntity> findByAtivoTrueOrderByOrdemAsc();*/
}
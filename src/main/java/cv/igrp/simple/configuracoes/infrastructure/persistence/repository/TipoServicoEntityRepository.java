package cv.igrp.simple.configuracoes.infrastructure.persistence.repository;

import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.TipoServicoEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;



@Repository
public interface TipoServicoEntityRepository extends
    JpaRepository<TipoServicoEntity, Integer>,
    JpaSpecificationExecutor<TipoServicoEntity>
{

    /*
    boolean existsByCodigo(String codigo);

    Optional<TipoServicoEntity> findByCodigo(String codigo);


    List<TipoServicoEntity> findByCategoriaId(Integer categoriaId);


    List<TipoServicoEntity> findByCategoriaIdAndAtivoTrue(Integer categoriaId);


    List<TipoServicoEntity> findByDisponivelPortalTrueAndAtivoTrue();


    List<TipoServicoEntity> findByRequerVistoriaTrue();


    List<TipoServicoEntity> findByRequerAnaliseTecTrue();


    List<TipoServicoEntity> findByRequerAprovacaoTrue();*/
}
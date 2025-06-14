package cv.igrp.simple.configuracoes.infrastructure.persistence;

import cv.igrp.simple.configuracoes.domain.models.ConfiguracoesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

@Repository
public interface ConfiguracoesRepository extends
    JpaRepository<ConfiguracoesEntity, Integer>,
    JpaSpecificationExecutor<ConfiguracoesEntity>
{
    /**
     * Busca configuração por chave.
     */
    Optional<ConfiguracoesEntity> findByChave(String chave);
    
    /**
     * Busca configurações por grupo.
     */
    List<ConfiguracoesEntity> findByGrupo(String grupo);
    
    /**
     * Busca configurações por tipo.
     */
    List<ConfiguracoesEntity> findByTipo(String tipo);
    
    /**
     * Busca configurações por grupo e tipo.
     */
    List<ConfiguracoesEntity> findByGrupoAndTipo(String grupo, String tipo);
}
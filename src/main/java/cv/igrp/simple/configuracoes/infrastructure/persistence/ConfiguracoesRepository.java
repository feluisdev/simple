package cv.igrp.simple.configuracoes.infrastructure.persistence;

import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.ConfiguracoesEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
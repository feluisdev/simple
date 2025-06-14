package cv.igrp.simple.configuracoes.application.queries.handlers;

import cv.igrp.simple.configuracoes.application.dto.ConfiguracoesResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ObterConfiguracaoQuery;
import cv.igrp.simple.configuracoes.domain.models.ConfiguracoesEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.ConfiguracoesRepository;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterConfiguracaoQueryHandler implements QueryHandler<ObterConfiguracaoQuery, ConfiguracoesResponseDTO> {

    private final ConfiguracoesRepository configuracoesRepository;

    @Override
    public ConfiguracoesResponseDTO handle(ObterConfiguracaoQuery query) {
        ConfiguracoesEntity configuracao = configuracoesRepository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Configuração não encontrada com o ID: " + query.getId()));

        return mapToDTO(configuracao);
    }

    private ConfiguracoesResponseDTO mapToDTO(ConfiguracoesEntity entity) {
        return ConfiguracoesResponseDTO.builder()
                .id(entity.getId())
                .chave(entity.getChave())
                .valor(entity.getValor())
                .descricao(entity.getDescricao())
                .tipo(entity.getTipo())
                .grupo(entity.getGrupo())
                .editavel(entity.getEditavel())
                .estado(entity.getEstado())
                .build();
    }
}
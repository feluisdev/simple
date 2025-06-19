package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.AtualizarConfiguracaoCommand;
import cv.igrp.simple.configuracoes.application.dto.ConfiguracoesResponseDTO;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.ConfiguracoesEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.ConfiguracoesRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtualizarConfiguracaoCommandHandler implements CommandHandler<AtualizarConfiguracaoCommand, ConfiguracoesResponseDTO> {

    private final ConfiguracoesRepository configuracoesRepository;

    @Override
    @Transactional
    public ConfiguracoesResponseDTO handle(AtualizarConfiguracaoCommand command) {
        ConfiguracoesEntity configuracao = configuracoesRepository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Configuração não encontrada com o ID: " + command.getId()));

        // Verificar se a chave está sendo alterada e se já existe outra configuração com a mesma chave
        if (command.getChave() != null && !command.getChave().equals(configuracao.getChave())) {
            Optional<ConfiguracoesEntity> existingConfiguracao = configuracoesRepository.findByChave(command.getChave());
            if (existingConfiguracao.isPresent() && !existingConfiguracao.get().getId().equals(command.getId())) {
                throw new IllegalStateException("Já existe uma configuração com a chave: " + command.getChave());
            }
            configuracao.setChave(command.getChave());
        }

        // Atualizar os campos se não forem nulos
        if (command.getValor() != null) {
            configuracao.setValor(command.getValor());
        }

        if (command.getDescricao() != null) {
            configuracao.setDescricao(command.getDescricao());
        }

        if (command.getTipo() != null) {
            configuracao.setTipo(command.getTipo());
        }

        if (command.getGrupo() != null) {
            configuracao.setGrupo(command.getGrupo());
        }

        if (command.getEditavel() != null) {
            configuracao.setEditavel(command.getEditavel());
        }

        if (command.getEstado() != null) {
            configuracao.setEstado(command.getEstado());
        }

        ConfiguracoesEntity updatedConfiguracao = configuracoesRepository.save(configuracao);

        return mapToDTO(updatedConfiguracao);
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
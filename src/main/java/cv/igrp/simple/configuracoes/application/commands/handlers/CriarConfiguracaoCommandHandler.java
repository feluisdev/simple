package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.CriarConfiguracaoCommand;
import cv.igrp.simple.configuracoes.application.constants.Estado;
import cv.igrp.simple.configuracoes.application.dto.ConfiguracoesResponseDTO;
import cv.igrp.simple.configuracoes.domain.models.ConfiguracoesEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.ConfiguracoesRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CriarConfiguracaoCommandHandler implements CommandHandler<CriarConfiguracaoCommand, ConfiguracoesResponseDTO> {

    private final ConfiguracoesRepository configuracoesRepository;

    @Override
    @Transactional
    public ConfiguracoesResponseDTO handle(CriarConfiguracaoCommand command) {
        // Verificar se já existe uma configuração com a mesma chave
        if (configuracoesRepository.findByChave(command.getChave()).isPresent()) {
            throw new IllegalStateException("Já existe uma configuração com a chave: " + command.getChave());
        }

        ConfiguracoesEntity configuracao = ConfiguracoesEntity.builder()
                .chave(command.getChave())
                .valor(command.getValor())
                .descricao(command.getDescricao())
                .tipo(command.getTipo())
                .grupo(command.getGrupo())
                .editavel(command.getEditavel())
                .estado(Estado.ATIVO)
                .build();

        ConfiguracoesEntity savedConfiguracao = configuracoesRepository.save(configuracao);

        return mapToDTO(savedConfiguracao);
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
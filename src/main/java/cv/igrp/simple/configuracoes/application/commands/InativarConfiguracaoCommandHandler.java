package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.simple.configuracoes.application.constants.Estado;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.ConfiguracoesEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.ConfiguracoesRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InativarConfiguracaoCommandHandler implements CommandHandler<InativarConfiguracaoCommand, String> {

    private final ConfiguracoesRepository configuracoesRepository;

    @Override
    @Transactional
    public String handle(InativarConfiguracaoCommand command) {
        ConfiguracoesEntity configuracao = configuracoesRepository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Configuração não encontrada com o ID: " + command.getId()));

        configuracao.setEstado(Estado.INATIVO);
        configuracoesRepository.save(configuracao);

        return "Configuração inativada com sucesso";
    }
}
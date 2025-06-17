package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.InativarTiposServicosCommand;
import cv.igrp.simple.configuracoes.domain.models.TiposServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.TiposServicosRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InativarTiposServicosCommandHandler implements CommandHandler<InativarTiposServicosCommand, Void> {

    private final TiposServicosRepository repository;

    @Override
    @Transactional
    public Void handle(InativarTiposServicosCommand command) {
        TiposServicosEntity entity = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de serviço não encontrado com o ID: " + command.getId()));

        entity.setAtivo(false);
        repository.save(entity);
        return null;
    }
}
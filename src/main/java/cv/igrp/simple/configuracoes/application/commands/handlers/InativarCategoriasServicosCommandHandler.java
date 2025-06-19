package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.InativarCategoriasServicosCommand;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InativarCategoriasServicosCommandHandler implements CommandHandler<InativarCategoriasServicosCommand, Void> {

    private final CategoriasServicosRepository repository;

    @Override
    @Transactional
    public Void handle(InativarCategoriasServicosCommand command) {
        CategoriasServicosEntity entity = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de serviço não encontrada com o ID: " + command.getId()));

        entity.setAtivo(false);
        repository.save(entity);
        return null;
    }
}
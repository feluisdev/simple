package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.UpdateCategoriasServicosCommand;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCategoriasServicosCommandHandler implements CommandHandler<UpdateCategoriasServicosCommand, Void> {

    private final CategoriasServicosRepository repository;

    @Override
    @Transactional
    public Void handle(UpdateCategoriasServicosCommand command) {
        CategoriasServicosEntity entity = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de serviço não encontrada com o ID: " + command.getId()));

        if (command.getNome() != null) {
            entity.setNome(command.getNome());
        }
        
        if (command.getDescricao() != null) {
            entity.setDescricao(command.getDescricao());
        }
        
        if (command.getIcone() != null) {
            entity.setIcone(command.getIcone());
        }
        
        if (command.getCor() != null) {
            entity.setCor(command.getCor());
        }
        
        if (command.getOrdem() != null) {
            entity.setOrdem(command.getOrdem());
        }
        
        if (command.getAtivo() != null) {
            entity.setAtivo(command.getAtivo());
        }

        repository.save(entity);
        return null;
    }
}
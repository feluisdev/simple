package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.CreateCategoriasServicosCommand;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCategoriasServicosCommandHandler implements CommandHandler<CreateCategoriasServicosCommand, Integer> {

    private final CategoriasServicosRepository repository;

    @Override
    @Transactional
    public Integer handle(CreateCategoriasServicosCommand command) {
        CategoriasServicosEntity entity = new CategoriasServicosEntity();
        entity.setNome(command.getNome());
        entity.setDescricao(command.getDescricao());
        entity.setIcone(command.getIcone());
        entity.setCor(command.getCor());
        entity.setOrdem(command.getOrdem());
        entity.setAtivo(command.getAtivo() != null ? command.getAtivo() : true);

        entity = repository.save(entity);
        return entity.getId();
    }
}
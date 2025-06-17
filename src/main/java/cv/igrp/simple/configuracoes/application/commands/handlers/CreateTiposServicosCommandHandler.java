package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.CreateTiposServicosCommand;
import cv.igrp.simple.configuracoes.domain.models.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.domain.models.TiposServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
import cv.igrp.simple.configuracoes.infrastructure.persistence.TiposServicosRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTiposServicosCommandHandler implements CommandHandler<CreateTiposServicosCommand, Integer> {

    private final TiposServicosRepository repository;
    private final CategoriasServicosRepository categoriasRepository;

    @Override
    @Transactional
    public Integer handle(CreateTiposServicosCommand command) {
        // Verificar se a categoria existe
        CategoriasServicosEntity categoria = categoriasRepository.findById(command.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de serviço não encontrada com o ID: " + command.getCategoriaId()));

        // Verificar se a categoria está ativa
        if (!categoria.getAtivo()) {
            throw new IllegalStateException("Não é possível criar um tipo de serviço para uma categoria inativa");
        }

        // Verificar se já existe um tipo de serviço com o mesmo código
        if (repository.existsByCodigo(command.getCodigo())) {
            throw new IllegalStateException("Já existe um tipo de serviço com o código: " + command.getCodigo());
        }

        TiposServicosEntity entity = new TiposServicosEntity();
        entity.setCategoriaId(command.getCategoriaId());
        entity.setCodigo(command.getCodigo());
        entity.setNome(command.getNome());
        entity.setDescricao(command.getDescricao());
        entity.setPrazoEstimado(command.getPrazoEstimado());
        entity.setValorBase(command.getValorBase());
        entity.setRequerVistoria(command.getRequerVistoria() != null ? command.getRequerVistoria() : false);
        entity.setRequerAnaliseTec(command.getRequerAnaliseTec() != null ? command.getRequerAnaliseTec() : false);
        entity.setRequerAprovacao(command.getRequerAprovacao() != null ? command.getRequerAprovacao() : false);
        entity.setDisponivelPortal(command.getDisponivelPortal() != null ? command.getDisponivelPortal() : false);
        entity.setAtivo(command.getAtivo() != null ? command.getAtivo() : true);

        entity = repository.save(entity);
        return entity.getId();
    }
}
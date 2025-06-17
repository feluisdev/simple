package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.UpdateTiposServicosCommand;
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
public class UpdateTiposServicosCommandHandler implements CommandHandler<UpdateTiposServicosCommand, Void> {

    private final TiposServicosRepository repository;
    private final CategoriasServicosRepository categoriasRepository;

    @Override
    @Transactional
    public Void handle(UpdateTiposServicosCommand command) {
        TiposServicosEntity entity = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de serviço não encontrado com o ID: " + command.getId()));

        // Verificar se a categoria existe, caso tenha sido fornecida
        if (command.getCategoriaId() != null) {
            CategoriasServicosEntity categoria = categoriasRepository.findById(command.getCategoriaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria de serviço não encontrada com o ID: " + command.getCategoriaId()));

            // Verificar se a categoria está ativa
            if (!categoria.getAtivo()) {
                throw new IllegalStateException("Não é possível associar um tipo de serviço a uma categoria inativa");
            }

            entity.setCategoriaId(command.getCategoriaId());
        }

        // Verificar se o código já existe, caso tenha sido fornecido e seja diferente do atual
        if (command.getCodigo() != null && !command.getCodigo().equals(entity.getCodigo())) {
            if (repository.existsByCodigo(command.getCodigo())) {
                throw new IllegalStateException("Já existe um tipo de serviço com o código: " + command.getCodigo());
            }
            entity.setCodigo(command.getCodigo());
        }

        if (command.getNome() != null) {
            entity.setNome(command.getNome());
        }

        if (command.getDescricao() != null) {
            entity.setDescricao(command.getDescricao());
        }

        if (command.getPrazoEstimado() != null) {
            entity.setPrazoEstimado(command.getPrazoEstimado());
        }

        if (command.getValorBase() != null) {
            entity.setValorBase(command.getValorBase());
        }

        if (command.getRequerVistoria() != null) {
            entity.setRequerVistoria(command.getRequerVistoria());
        }

        if (command.getRequerAnaliseTec() != null) {
            entity.setRequerAnaliseTec(command.getRequerAnaliseTec());
        }

        if (command.getRequerAprovacao() != null) {
            entity.setRequerAprovacao(command.getRequerAprovacao());
        }

        if (command.getDisponivelPortal() != null) {
            entity.setDisponivelPortal(command.getDisponivelPortal());
        }

        if (command.getAtivo() != null) {
            entity.setAtivo(command.getAtivo());
        }

        repository.save(entity);
        return null;
    }
}
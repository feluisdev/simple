package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.StatusPedidoEntity;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UpdateStatusPedidoCommandHandler implements CommandHandler<UpdateStatusPedidoCommand, Void> {

    //private final StatusPedidoRepository repository;

    @Override
    @Transactional
    public Void handle(UpdateStatusPedidoCommand command) {
        // Buscar a entidade existente
      /*  StatusPedidoEntity entity = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Status de pedido não encontrado"));

        // Verificar se o código foi alterado e se já existe outro status com o mesmo código
        if (StringUtils.hasText(command.getCodigo()) && !command.getCodigo().equals(entity.getCodigo())) {
            if (repository.existsByCodigo(command.getCodigo())) {
                throw new IllegalStateException("Já existe um status de pedido com o código " + command.getCodigo());
            }
            entity.setCodigo(command.getCodigo());
        }

        // Atualizar os campos se fornecidos
        if (StringUtils.hasText(command.getNome())) {
            entity.setNome(command.getNome());
        }

        entity.setDescricao(command.getDescricao());

        if (StringUtils.hasText(command.getCor())) {
            entity.setCor(command.getCor());
        }

        if (StringUtils.hasText(command.getIcone())) {
            entity.setIcone(command.getIcone());
        }

        if (command.getOrdem() != null) {
            entity.setOrdem(command.getOrdem());
        }

        if (command.getVisivelPortal() != null) {
            entity.setVisivelPortal(command.getVisivelPortal());
        }

        // Salvar as alterações
        repository.save(entity);*/
        return null;
    }

}
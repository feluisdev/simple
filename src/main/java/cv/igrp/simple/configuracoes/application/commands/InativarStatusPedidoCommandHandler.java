package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.StatusPedidoEntity;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InativarStatusPedidoCommandHandler implements CommandHandler<InativarStatusPedidoCommand, Void> {

    //private final StatusPedidoRepository repository;

    @Override
    @Transactional
    public Void handle(InativarStatusPedidoCommand command) {
        // Buscar a entidade existente
        /*StatusPedidoEntity entity = repository.findById(command.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Status de pedido não encontrado"));

        // Inativar o status definindo visivelPortal como false
        entity.setVisivelPortal(false);

        // Salvar as alterações
        repository.save(entity);*/
        return null;
    }
}
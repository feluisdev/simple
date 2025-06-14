package cv.igrp.simple.configuracoes.application.commands.handlers;

import cv.igrp.simple.configuracoes.application.commands.commands.CreateStatusPedidoCommand;
import cv.igrp.simple.configuracoes.domain.models.StatusPedidoEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.StatusPedidoRepository;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateStatusPedidoCommandHandler implements CommandHandler<CreateStatusPedidoCommand, Integer> {

    private final StatusPedidoRepository repository;

    @Override
    @Transactional
    public Integer handle(CreateStatusPedidoCommand command) {
        // Verificar se já existe um status com o mesmo código
        if (repository.existsByCodigo(command.getCodigo())) {
            throw new IllegalStateException("Já existe um status de pedido com o código " + command.getCodigo());
        }

        // Criar e salvar a entidade
        StatusPedidoEntity entity = StatusPedidoEntity.builder()
                .codigo(command.getCodigo())
                .nome(command.getNome())
                .descricao(command.getDescricao())
                .cor(command.getCor())
                .icone(command.getIcone())
                .ordem(command.getOrdem())
                .visivelPortal(command.getVisivelPortal())
                .build();

        StatusPedidoEntity savedEntity = repository.save(entity);
        return savedEntity.getId();
    }
}
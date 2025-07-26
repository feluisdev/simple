package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class InativarStatusPedidoCommandHandler implements CommandHandler<InativarStatusPedidoCommand, ResponseEntity<Map<String, ?>>> {

    private final StatusPedidoRepository repository;

    @Override
    @Transactional
    public ResponseEntity<Map<String, ?>> handle(InativarStatusPedidoCommand command) {

        var domain = repository
                .getById(Identificador.from(command.getStatusPedidoId()))
                .orElseThrow(() ->
                        new IllegalArgumentException("Status pedido not found with id"+command.getStatusPedidoId()));

        domain.inativar();

        repository.save(domain);

        var response = Map.of(
                "id", domain.getId(),
                "statusPedidoUuid", domain.getStatusPedidoUuid().getStringValor(),
                "message", "Status Pedido inativado com sucesso."
        );

        return ResponseEntity.ok(response);
    }
}
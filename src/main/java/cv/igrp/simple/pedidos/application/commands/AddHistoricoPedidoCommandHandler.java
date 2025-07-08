package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class AddHistoricoPedidoCommandHandler implements CommandHandler<AddHistoricoPedidoCommand, ResponseEntity<Map<String, ?>>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddHistoricoPedidoCommandHandler.class);

    private final PedidoRepository pedidoRepository;
    private final StatusPedidoRepository statusPedidoRepository;

    public AddHistoricoPedidoCommandHandler(PedidoRepository pedidoRepository, StatusPedidoRepository statusPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.statusPedidoRepository = statusPedidoRepository;
    }


    @IgrpCommandHandler
    public ResponseEntity<Map<String, ?>> handle(AddHistoricoPedidoCommand command) {
        var pedidoId = Identificador.from(command.getPedidoId());

        var dto = command.getCreatehistoricopedido();

        var statusPedidoId = Identificador.from(dto.getStatusId());

        var pedido = pedidoRepository.findById(pedidoId).orElseThrow(
                () -> IgrpResponseStatusException.notFound("Pedido nao encontrado")
        );

        var statusPedido = statusPedidoRepository.getById(statusPedidoId).orElseThrow(
                () -> IgrpResponseStatusException.notFound("status pedido nao encontrado")
        );

        pedido.registarHistorico(dto.getObservacao(), statusPedido);

        pedidoRepository.save(pedido);

        return ResponseEntity.ok(Map.of("sucesso", "historico adicionado com sucesso"));
    }

}
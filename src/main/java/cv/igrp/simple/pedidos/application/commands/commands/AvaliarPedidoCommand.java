package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.pedidos.application.dto.CreateAvaliacaoPedidoDTO;

import java.util.UUID;

public class AvaliarPedidoCommand implements Command {

    private final CreateAvaliacaoPedidoDTO createAvaliacaoPedidoDTO;
    private final UUID pedidoId;

    public AvaliarPedidoCommand(CreateAvaliacaoPedidoDTO createAvaliacaoPedidoDTO, UUID pedidoId) {
        this.createAvaliacaoPedidoDTO = createAvaliacaoPedidoDTO;
        this.pedidoId = pedidoId;
    }

    public CreateAvaliacaoPedidoDTO getCreateAvaliacaoPedidoDTO() {
        return createAvaliacaoPedidoDTO;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }
}
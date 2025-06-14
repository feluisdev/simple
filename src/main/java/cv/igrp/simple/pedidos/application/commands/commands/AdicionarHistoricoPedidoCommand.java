package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.pedidos.application.dto.CreateHistoricoPedidoDTO;

import java.util.UUID;

public class AdicionarHistoricoPedidoCommand implements Command {

    private final CreateHistoricoPedidoDTO createHistoricoPedidoDTO;
    private final UUID pedidoId;

    public AdicionarHistoricoPedidoCommand(CreateHistoricoPedidoDTO createHistoricoPedidoDTO, UUID pedidoId) {
        this.createHistoricoPedidoDTO = createHistoricoPedidoDTO;
        this.pedidoId = pedidoId;
    }

    public CreateHistoricoPedidoDTO getCreateHistoricoPedidoDTO() {
        return createHistoricoPedidoDTO;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }
}
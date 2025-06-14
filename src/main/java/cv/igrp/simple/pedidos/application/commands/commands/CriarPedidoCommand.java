package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.pedidos.application.dto.CreatePedidoDTO;

import java.util.UUID;

public class CriarPedidoCommand implements Command {

    private final CreatePedidoDTO createPedidoDTO;

    public CriarPedidoCommand(CreatePedidoDTO createPedidoDTO) {
        this.createPedidoDTO = createPedidoDTO;
    }

    public CreatePedidoDTO getCreatePedidoDTO() {
        return createPedidoDTO;
    }
}
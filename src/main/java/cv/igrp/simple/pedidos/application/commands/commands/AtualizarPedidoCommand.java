package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.pedidos.application.dto.UpdatePedidoDTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AtualizarPedidoCommand implements Command {

    private final UpdatePedidoDTO updatePedidoDTO;
    private final Integer id;

}
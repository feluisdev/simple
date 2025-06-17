package cv.igrp.simple.pedidos.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.pedidos.application.dto.CreatePagamentoPedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Comando para registrar um novo pagamento de pedido
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrarPagamentoPedidoCommand implements Command {

    private CreatePagamentoPedidoDTO createPagamentoPedidoDTO;
    private Integer pedidoId;
}
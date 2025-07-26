package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.pedidos.application.dto.CreateHistoricoPedidoDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddHistoricoPedidoCommand implements Command {

  
  private CreateHistoricoPedidoDTO createhistoricopedido;
  @NotBlank(message = "The field <pedidoId> is required.")
  private String pedidoId;

}
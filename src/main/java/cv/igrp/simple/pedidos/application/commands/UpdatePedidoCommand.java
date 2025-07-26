package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.pedidos.application.dto.PedidoRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePedidoCommand implements Command {

  
  private PedidoRequestDTO pedidorequest;
  @NotBlank(message = "The field <pedidoId> is required.")
  private String pedidoId;

}
package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.pedidos.application.dto.CreateAvaliacaoPedidoDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAvaliacaoPedidoCommand implements Command {

  
  private CreateAvaliacaoPedidoDTO createavaliacaopedido;
  @NotBlank(message = "The field <pedidoId> is required.")
  private String pedidoId;

}
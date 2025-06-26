package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePagamentoPedidoCommand implements Command {

  @NotBlank(message = "The field <pedidoId> is required.")
  private String pedidoId;
  @NotBlank(message = "The field <pagamentoId> is required.")
  private String pagamentoId;

}
package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPagamentoPedidoByIdQuery implements Query {

  @NotBlank(message = "The field <pedidoId> is required.")
  private String pedidoId;
  @NotBlank(message = "The field <pagamentoId> is required.")
  private String pagamentoId;

}
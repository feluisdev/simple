package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetPedidoByUtenteQuery implements Query {

  @NotBlank(message = "The field <utenteId> is required.")
  private String utenteId;

}
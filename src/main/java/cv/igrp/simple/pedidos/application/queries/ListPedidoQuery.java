package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListPedidoQuery implements Query {

  @NotBlank(message = "The field <pedidoId> is required.")
  private String pedidoId;
  @NotBlank(message = "The field <tipoServicoId> is required.")
  private String tipoServicoId;
  @NotBlank(message = "The field <codigoAcompanhamento> is required.")
  private String codigoAcompanhamento;
  @NotBlank(message = "The field <utenteId> is required.")
  private String utenteId;
  @NotBlank(message = "The field <pagina> is required.")
  private String pagina;
  @NotBlank(message = "The field <tamanho> is required.")
  private String tamanho;

}
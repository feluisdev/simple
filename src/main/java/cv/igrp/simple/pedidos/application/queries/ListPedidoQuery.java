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

  @NotBlank(message = "The field <tipoServicoId> is required.")
  private String tipoServicoId;
  @NotBlank(message = "The field <codigoAcompanhamento> is required.")
  private String codigoAcompanhamento;
  @NotNull(message = "The field <utenteId> is required.")
  private Integer utenteId;
  @NotBlank(message = "The field <nomeUtente> is required.")
  private String nomeUtente;
  @NotBlank(message = "The field <numeroUtente> is required.")
  private String numeroUtente;
  @NotBlank(message = "The field <dataDe> is required.")
  private String dataDe;
  @NotBlank(message = "The field <dataAte> is required.")
  private String dataAte;
  @NotBlank(message = "The field <pagina> is required.")
  private String pagina;
  @NotBlank(message = "The field <tamanho> is required.")
  private String tamanho;

}
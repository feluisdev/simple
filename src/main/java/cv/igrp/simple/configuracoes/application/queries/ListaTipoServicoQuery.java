package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaTipoServicoQuery implements Query {

  @NotBlank(message = "The field <nome> is required.")
  private String nome;
  @NotBlank(message = "The field <codigo> is required.")
  private String codigo;
  @NotBlank(message = "The field <pagina> is required.")
  private String pagina;
  @NotBlank(message = "The field <tamanho> is required.")
  private String tamanho;

}
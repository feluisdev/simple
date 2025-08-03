package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEstabelecimentosQuery implements Query {

  @NotBlank(message = "The field <gerente> is required")
  private String gerente;
  @NotBlank(message = "The field <pagina> is required")
  private String pagina;
  @NotBlank(message = "The field <tamanho> is required")
  private String tamanho;

}
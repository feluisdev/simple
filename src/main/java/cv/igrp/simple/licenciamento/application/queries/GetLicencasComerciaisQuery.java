package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLicencasComerciaisQuery implements Query {

  @NotBlank(message = "The field <alvara> is required")
  private String alvara;
  @NotBlank(message = "The field <EstadoLicenca> is required")
  private String estadoLicenca;
  @NotBlank(message = "The field <idEstabelecimento> is required")
  private String idEstabelecimento;
  @NotBlank(message = "The field <pagina> is required")
  private String pagina;
  @NotBlank(message = "The field <tamanho> is required")
  private String tamanho;

}
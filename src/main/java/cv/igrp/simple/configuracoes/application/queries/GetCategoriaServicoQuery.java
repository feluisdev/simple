package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoriaServicoQuery implements Query {

  @NotBlank(message = "The field <categoriaServicoId> is required.")
  private String categoriaServicoId;

}
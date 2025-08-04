package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetClasseByIdQuery implements Query {

  @NotBlank(message = "The field <idClasse> is required")
  private String idClasse;

}
package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetLicencaComercialByIDQuery implements Query {

  @NotBlank(message = "The field <idLicenca> is required")
  private String idLicenca;

}
package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.licenciamento.application.dto.LicencaRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLicencaComercialCommand implements Command {

  
  private LicencaRequestDTO licencarequest;
  @NotBlank(message = "The field <idLicenca> is required")
  private String idLicenca;

}
package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.licenciamento.application.dto.ClasseRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClasseCommand implements Command {

  
  private ClasseRequestDTO classerequest;
  @NotBlank(message = "The field <classeId> is required")
  private String classeId;

}
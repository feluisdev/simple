package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.licenciamento.application.dto.SectorRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSectorCommand implements Command {

  
  private SectorRequestDTO sectorrequest;
  @NotBlank(message = "The field <sectorId> is required")
  private String sectorId;

}
package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.licenciamento.application.dto.CategoryRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryLicenciamentoCommand implements Command {

  
  private CategoryRequestDTO categoryrequest;

}
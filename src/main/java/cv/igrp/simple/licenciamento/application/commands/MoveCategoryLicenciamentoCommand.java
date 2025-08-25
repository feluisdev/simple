package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.licenciamento.application.dto.MoveCategoryDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveCategoryLicenciamentoCommand implements Command {

  
  private MoveCategoryDTO movecategory;
  @NotBlank(message = "The field <categoryId> is required")
  private String categoryId;

}
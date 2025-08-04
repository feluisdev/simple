package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEstabelecimentoCommand implements Command {

  
  private EstabelecimentoRequestDTO estabelecimentorequest;
  @NotBlank(message = "The field <idEstabelecimento> is required")
  private String idEstabelecimento;

}
package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTipoAtividadeCommand implements Command {

  
  private TipoAtividadeRequestDTO tipoatividaderequest;
  @NotBlank(message = "The field <idTipoAtividade> is required")
  private String idTipoAtividade;

}
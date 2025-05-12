package cv.igrp.simple.utente.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.utente.application.dto.AdicionarServicoDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdicionarServicoUtenteCommand implements Command {

  
  private AdicionarServicoDTO adicionarservico;
  @NotNull(message = "The field <utenteId> is required.")
  private Integer utenteId;
  @NotNull(message = "The field <servicoId> is required.")
  private Integer servicoId;

}
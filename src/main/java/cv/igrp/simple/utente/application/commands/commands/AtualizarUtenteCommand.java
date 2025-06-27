package cv.igrp.simple.utente.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.utente.application.dto.UpdateUtenteDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizarUtenteCommand implements Command {

  
  private UpdateUtenteDTO updateutente;
  @NotNull(message = "The field <id> is required.")
  private Integer id;

}
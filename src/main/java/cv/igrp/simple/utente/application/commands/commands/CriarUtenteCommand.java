package cv.igrp.simple.utente.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.utente.application.dto.CriarUtenteDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarUtenteCommand implements Command {

  
  private CriarUtenteDTO criarutente;

}
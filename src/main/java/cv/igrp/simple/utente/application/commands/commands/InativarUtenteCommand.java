package cv.igrp.simple.utente.application.commands.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InativarUtenteCommand implements Command {

  @NotNull(message = "The field <id> is required.")
  private Integer id;

}
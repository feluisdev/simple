package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesativarEstabelecimentoCommand implements Command {

  @NotBlank(message = "The field <idEstabelecimento> is required")
  private String idEstabelecimento;

}
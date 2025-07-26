package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InativarCategoriaServicoCommand implements Command {

  @NotBlank(message = "The field <categoriaServicoId> is required.")
  private String categoriaServicoId;

}
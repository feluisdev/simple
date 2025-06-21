package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.configuracoes.application.dto.CriarTiposServicosDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTipoServicoCommand implements Command {

  
  private CriarTiposServicosDTO criartiposservicos;
  @NotBlank(message = "The field <tipoServicoId> is required.")
  private String tipoServicoId;

}
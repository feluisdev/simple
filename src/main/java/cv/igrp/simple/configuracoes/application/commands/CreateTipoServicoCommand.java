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
public class CreateTipoServicoCommand implements Command {

  
  private CriarTiposServicosDTO criartiposservicos;

}
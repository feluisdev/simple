package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.configuracoes.application.dto.CreateStatusPedidoDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStatusPedidoCommand implements Command {

  
  private CreateStatusPedidoDTO createstatuspedido;

}
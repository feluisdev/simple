package cv.igrp.simple.pedido.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.pedido.application.dto.EtapaProcessoRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEtapaProcessoCommand implements Command {

  
  private EtapaProcessoRequestDTO etapaprocessorequest;

}
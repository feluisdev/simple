package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.configuracoes.application.dto.CriarTiposServicosDTO;

@Component
public class UpdateTipoServicoCommandHandler implements CommandHandler<UpdateTipoServicoCommand, ResponseEntity<TiposServicosResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTipoServicoCommandHandler.class);

   public UpdateTipoServicoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<TiposServicosResponseDTO> handle(UpdateTipoServicoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
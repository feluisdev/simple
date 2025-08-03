package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeResponseDTO;

@Component
public class UpdateTipoAtividadeCommandHandler implements CommandHandler<UpdateTipoAtividadeCommand, ResponseEntity<TipoAtividadeResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTipoAtividadeCommandHandler.class);

   public UpdateTipoAtividadeCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<TipoAtividadeResponseDTO> handle(UpdateTipoAtividadeCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
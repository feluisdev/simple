package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;

@Component
public class UpdateStatusCommandHandler implements CommandHandler<UpdateStatusCommand, ResponseEntity<PedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateStatusCommandHandler.class);

   public UpdateStatusCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<PedidoResponseDTO> handle(UpdateStatusCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;

@Component
public class UpdateAvaliacaoCommandHandler implements CommandHandler<UpdateAvaliacaoCommand, ResponseEntity<AvaliacaoPedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAvaliacaoCommandHandler.class);

   public UpdateAvaliacaoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<AvaliacaoPedidoResponseDTO> handle(UpdateAvaliacaoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
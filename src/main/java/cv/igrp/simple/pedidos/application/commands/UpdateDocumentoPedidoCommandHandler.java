package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.DocumentoPedidoResponseDTO;

@Component
public class UpdateDocumentoPedidoCommandHandler implements CommandHandler<UpdateDocumentoPedidoCommand, ResponseEntity<DocumentoPedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDocumentoPedidoCommandHandler.class);

   public UpdateDocumentoPedidoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<DocumentoPedidoResponseDTO> handle(UpdateDocumentoPedidoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
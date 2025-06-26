package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class AddDocumentoPedidoCommandHandler implements CommandHandler<AddDocumentoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(AddDocumentoPedidoCommandHandler.class);

   public AddDocumentoPedidoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(AddDocumentoPedidoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
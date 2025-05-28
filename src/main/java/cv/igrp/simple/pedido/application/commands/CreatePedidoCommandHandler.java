package cv.igrp.simple.pedido.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class CreatePedidoCommandHandler implements CommandHandler<CreatePedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreatePedidoCommandHandler.class);

   public CreatePedidoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(CreatePedidoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
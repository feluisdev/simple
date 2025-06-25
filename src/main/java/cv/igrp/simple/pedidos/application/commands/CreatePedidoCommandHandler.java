package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
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
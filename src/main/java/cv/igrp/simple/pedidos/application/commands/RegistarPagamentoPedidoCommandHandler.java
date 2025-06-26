package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class RegistarPagamentoPedidoCommandHandler implements CommandHandler<RegistarPagamentoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(RegistarPagamentoPedidoCommandHandler.class);

   public RegistarPagamentoPedidoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(RegistarPagamentoPedidoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
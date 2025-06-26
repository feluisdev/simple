package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class AddAvaliacaoPedidoCommandHandler implements CommandHandler<AddAvaliacaoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(AddAvaliacaoPedidoCommandHandler.class);

   public AddAvaliacaoPedidoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(AddAvaliacaoPedidoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
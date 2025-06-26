package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;

@Component
public class UpdatePagamentoPedidoCommandHandler implements CommandHandler<UpdatePagamentoPedidoCommand, ResponseEntity<PagamentoPedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePagamentoPedidoCommandHandler.class);

   public UpdatePagamentoPedidoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<PagamentoPedidoResponseDTO> handle(UpdatePagamentoPedidoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
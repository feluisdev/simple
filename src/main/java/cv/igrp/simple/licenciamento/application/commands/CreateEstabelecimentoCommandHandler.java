package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseDTO;

@Component
public class CreateEstabelecimentoCommandHandler implements CommandHandler<CreateEstabelecimentoCommand, ResponseEntity<EstabelecimentoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateEstabelecimentoCommandHandler.class);

   public CreateEstabelecimentoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<EstabelecimentoResponseDTO> handle(CreateEstabelecimentoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
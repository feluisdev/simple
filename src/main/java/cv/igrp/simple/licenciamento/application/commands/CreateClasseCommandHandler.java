package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;

@Component
public class CreateClasseCommandHandler implements CommandHandler<CreateClasseCommand, ResponseEntity<ClasseResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateClasseCommandHandler.class);

   public CreateClasseCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<ClasseResponseDTO> handle(CreateClasseCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
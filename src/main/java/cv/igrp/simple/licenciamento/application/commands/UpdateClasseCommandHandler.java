package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;

@Component
public class UpdateClasseCommandHandler implements CommandHandler<UpdateClasseCommand, ResponseEntity<ClasseResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateClasseCommandHandler.class);

   public UpdateClasseCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<ClasseResponseDTO> handle(UpdateClasseCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
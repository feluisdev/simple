package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;

@Component
public class CreateLicencaComercialCommandHandler implements CommandHandler<CreateLicencaComercialCommand, ResponseEntity<LicencaResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateLicencaComercialCommandHandler.class);

   public CreateLicencaComercialCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<LicencaResponseDTO> handle(CreateLicencaComercialCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
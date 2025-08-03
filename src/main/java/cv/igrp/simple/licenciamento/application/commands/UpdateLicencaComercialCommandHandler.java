package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;

@Component
public class UpdateLicencaComercialCommandHandler implements CommandHandler<UpdateLicencaComercialCommand, ResponseEntity<LicencaResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateLicencaComercialCommandHandler.class);

   public UpdateLicencaComercialCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<LicencaResponseDTO> handle(UpdateLicencaComercialCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
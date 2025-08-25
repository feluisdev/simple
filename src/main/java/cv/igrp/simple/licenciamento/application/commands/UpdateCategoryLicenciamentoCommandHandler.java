package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.CategoryResponseDTO;

@Component
public class UpdateCategoryLicenciamentoCommandHandler implements CommandHandler<UpdateCategoryLicenciamentoCommand, ResponseEntity<CategoryResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoryLicenciamentoCommandHandler.class);

   public UpdateCategoryLicenciamentoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<CategoryResponseDTO> handle(UpdateCategoryLicenciamentoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
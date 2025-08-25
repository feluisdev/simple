package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class MoveCategoryLicenciamentoCommandHandler implements CommandHandler<MoveCategoryLicenciamentoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(MoveCategoryLicenciamentoCommandHandler.class);

   public MoveCategoryLicenciamentoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(MoveCategoryLicenciamentoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
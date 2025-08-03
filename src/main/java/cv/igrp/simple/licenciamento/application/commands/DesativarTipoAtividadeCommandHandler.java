package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@Component
public class DesativarTipoAtividadeCommandHandler implements CommandHandler<DesativarTipoAtividadeCommand, ResponseEntity<String>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DesativarTipoAtividadeCommandHandler.class);

   public DesativarTipoAtividadeCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<String> handle(DesativarTipoAtividadeCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
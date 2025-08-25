package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Component
public class DesativarSectorCommandHandler implements CommandHandler<DesativarSectorCommand, ResponseEntity<Collection<String>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DesativarSectorCommandHandler.class);

   public DesativarSectorCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Collection<String>> handle(DesativarSectorCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
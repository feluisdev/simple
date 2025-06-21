package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;

@Component
public class UpdateCategoriaServicoCommandHandler implements CommandHandler<UpdateCategoriaServicoCommand, ResponseEntity<CategoriasServicosResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoriaServicoCommandHandler.class);

   public UpdateCategoriaServicoCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<CategoriasServicosResponseDTO> handle(UpdateCategoriaServicoCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}
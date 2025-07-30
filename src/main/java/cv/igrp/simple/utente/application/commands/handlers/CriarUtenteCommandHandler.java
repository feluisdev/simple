package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.application.commands.RegistarPagamentoPedidoCommandHandler;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.utente.application.commands.commands.CriarUtenteCommand;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteEntityRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CriarUtenteCommandHandler implements CommandHandler<CriarUtenteCommand, ResponseEntity<UtenteResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CriarUtenteCommandHandler.class);

   private final UtenteEntityRepository utenteRepository;

   private final UtenteService numeroUtenteService;
   private final UtenteMapper utenteMapper;


   public CriarUtenteCommandHandler(UtenteEntityRepository utenteRepository, UtenteService numeroUtenteService, UtenteMapper utenteMapper) {

       this.utenteRepository = utenteRepository;
       this.numeroUtenteService = numeroUtenteService;
       this.utenteMapper = utenteMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<UtenteResponseDTO> handle(CriarUtenteCommand command) {
      LOGGER.info("Received request to create new utente");
      UtenteEntity utente = utenteMapper.toUtente(command.getCriarutente());
      String numeroUtente = numeroUtenteService.geraNumeroUtente();
      LOGGER.debug("Generated utente number: {}", numeroUtente);
      utente.setNumero(numeroUtente);

      LOGGER.info("Saving utente to the database...");
      var utenteSaved =  utenteRepository.save(utente);

      UtenteResponseDTO responseDTO = utenteMapper.toUtenteResponseDTO(utenteSaved);

      return ResponseEntity.status(201).body(responseDTO);
   }

}
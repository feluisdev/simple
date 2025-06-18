package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.utente.application.commands.commands.CriarUtenteCommand;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.domain.models.UtenteEntity;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteEntityRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CriarUtenteCommandHandler implements CommandHandler<CriarUtenteCommand, ResponseEntity<UtenteResponseDTO>> {

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

      UtenteEntity utente = utenteMapper.toUtente(command.getCriarutente());
      String numeroUtente = numeroUtenteService.geraNumeroUtente();
      utente.setNumero(numeroUtente);

      var utenteSaved =  utenteRepository.save(utente);

      UtenteResponseDTO responseDTO = utenteMapper.toUtenteResponseDTO(utenteSaved);

      return ResponseEntity.status(201).body(responseDTO);
   }

}
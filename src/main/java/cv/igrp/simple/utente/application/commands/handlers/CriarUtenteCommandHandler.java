package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.utente.application.commands.commands.CriarUtenteCommand;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CriarUtenteCommandHandler implements CommandHandler<CriarUtenteCommand, ResponseEntity<UtenteResponseDTO>> {

   private final UtenteRepository utenteRepository;

   private final UtenteService numeroUtenteService;
   private final UtenteMapper utenteMapper;


   public CriarUtenteCommandHandler(UtenteRepository utenteRepository, UtenteService numeroUtenteService, UtenteMapper utenteMapper) {

       this.utenteRepository = utenteRepository;
       this.numeroUtenteService = numeroUtenteService;
       this.utenteMapper = utenteMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<UtenteResponseDTO> handle(CriarUtenteCommand command) {
      // TODO: Implement the command handling logic here
      Utente utente = utenteMapper.toUtente(command.getCriarutente());
      String numeroUtente = numeroUtenteService.geraNumeroUtente();
      utente.setNrUtente(numeroUtente);

      var utenteSaved =  utenteRepository.save(utente);

      UtenteResponseDTO responseDTO = utenteMapper.toUtenteResponseDTO(utenteSaved);

      return ResponseEntity.status(201).body(responseDTO);
   }

}
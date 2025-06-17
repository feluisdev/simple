package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.domain.models.UtenteEntity;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteEntityRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import cv.igrp.simple.utente.application.commands.commands.InativarUtenteCommand;



@Service
public class InativarUtenteCommandHandler implements CommandHandler<InativarUtenteCommand, ResponseEntity<String>> {


   private final UtenteService utenteService;

   private final UtenteEntityRepository utenteRepository;

   public InativarUtenteCommandHandler(UtenteService utenteService, UtenteEntityRepository utenteRepository) {

       this.utenteService = utenteService;
       this.utenteRepository = utenteRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<String> handle(InativarUtenteCommand command) {
      Integer idUtente = command.getId();
      UtenteEntity utente = utenteService.obterUtentePorId(idUtente);
      utente.setEstado(Estado.INATIVO);
      utenteRepository.save(utente);
      return ResponseEntity.ok("Utente inativado com sucesso");
   }

}
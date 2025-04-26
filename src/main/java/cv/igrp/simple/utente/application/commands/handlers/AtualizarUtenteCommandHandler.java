package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.utente.application.commands.commands.AtualizarUtenteCommand;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUtenteCommandHandler implements CommandHandler<AtualizarUtenteCommand, ResponseEntity<UtenteResponseDTO>> {

   private final UtenteRepository utenteRepository;

   private final UtenteMapper utenteMapper;

   private final UtenteService utenteService;


   public AtualizarUtenteCommandHandler(UtenteRepository utenteRepository, UtenteMapper utenteMapper, UtenteService utenteService) {

       this.utenteRepository = utenteRepository;
       this.utenteMapper = utenteMapper;
       this.utenteService = utenteService;
   }

   @IgrpCommandHandler
   public ResponseEntity<UtenteResponseDTO> handle(AtualizarUtenteCommand command) {
      // TODO: Implement the command handling logic here
      Integer idUtente = Integer.parseInt(command.getId());
      Utente utente = utenteService.obterUtentePorId(idUtente);
      var dto = command.getUpdateutente();

      utente.setNome(dto.getNome());
      utente.setTelefone(dto.getTelefone());
      utente.setEmail(dto.getEmail());
      utente.setMorada(dto.getMorada());

      var utenteUpdated = utenteRepository.save(utente);
      UtenteResponseDTO responseDTO = utenteMapper.toUtenteResponseDTO(utenteUpdated);

      return ResponseEntity.ok(responseDTO);
   }

}
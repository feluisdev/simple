package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.utente.application.commands.commands.AtualizarUtenteCommand;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.application.constants.TipoUtente;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.domain.models.UtenteEntity;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteEntityRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUtenteCommandHandler implements CommandHandler<AtualizarUtenteCommand, ResponseEntity<UtenteResponseDTO>> {

   private final UtenteEntityRepository utenteRepository;


   private final UtenteMapper utenteMapper;

   private final UtenteService utenteService;


   public AtualizarUtenteCommandHandler(UtenteEntityRepository utenteRepository, UtenteMapper utenteMapper, UtenteService utenteService) {


       this.utenteRepository = utenteRepository;
       this.utenteMapper = utenteMapper;
       this.utenteService = utenteService;
   }

   @IgrpCommandHandler
   public ResponseEntity<UtenteResponseDTO> handle(AtualizarUtenteCommand command) {
      // TODO: Implement the command handling logic here
      Integer idUtente = command.getId();
      UtenteEntity utente = utenteService.obterUtentePorId(idUtente);
      var dto = command.getUpdateutente();

      utente.setNome(dto.getNome());
      utente.setTelefone(dto.getTelefone());
      utente.setEmail(dto.getEmail());
      utente.setEndereco(dto.getEndereco());
      utente.setEstado(dto.getEstado());
      utente.setDataNascimento(dto.getDataNascimento());
      utente.setIdentificacao(dto.getIdentificacao());
      utente.setTipoIdentificacao(dto.getTipoIdentificacao());
      utente.setCaixaPostal(dto.getCaixaPostal());
      utente.setDepartamentoResponsavel(dto.getDepartamentoResponsavel());
      utente.setTipoUtente(dto.getTipoUtente());
      utente.setNomeMae(dto.getNomeMae());
      utente.setNomePai(dto.getNomePai());
      utente.setNif(dto.getNif());
      

      var utenteUpdated = utenteRepository.save(utente);
      UtenteResponseDTO responseDTO = utenteMapper.toUtenteResponseDTO(utenteUpdated);

      return ResponseEntity.ok(responseDTO);
   }

}
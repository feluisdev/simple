package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.utente.application.commands.commands.AtualizarUtenteCommand;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AtualizarUtenteCommandHandler implements CommandHandler<AtualizarUtenteCommand, ResponseEntity<UtenteResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(AtualizarUtenteCommandHandler.class);

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

      LOGGER.info("Iniciando atualização do utente com ID {}", command.getId());

      try {
         // Obter o ID do utente a ser atualizado
         Integer idUtente = command.getId();
         // Buscar o utente existente pelo ID
         UtenteEntity utente = utenteService.obterUtentePorId(idUtente);
         var dto = command.getUpdateutente();


         // Atualizar os campos do utente existente com os dados do DTO
         if (dto.getNome() != null) utente.setNome(dto.getNome());
         if (dto.getTelefone() != null) utente.setTelefone(dto.getTelefone());
         if (dto.getEmail() != null) utente.setEmail(dto.getEmail());
         if (dto.getEndereco() != null) utente.setEndereco(dto.getEndereco());
         if (dto.getDataNascimento() != null) utente.setDataNascimento(dto.getDataNascimento());
         if (dto.getIdentificacao() != null) utente.setIdentificacao(dto.getIdentificacao());
         if (dto.getTipoIdentificacao() != null) utente.setTipoIdentificacao(dto.getTipoIdentificacao());
         if (dto.getCaixaPostal() != null) utente.setCaixaPostal(dto.getCaixaPostal());
         if (dto.getDepartamentoResponsavel() != null)
            utente.setDepartamentoResponsavel(dto.getDepartamentoResponsavel());
         if (dto.getTipoUtente() != null) utente.setTipoUtente(dto.getTipoUtente());
         if (dto.getNomeMae() != null) utente.setNomeMae(dto.getNomeMae());
         if (dto.getNomePai() != null) utente.setNomePai(dto.getNomePai());
         if (dto.getNif() != null) utente.setNif(dto.getNif());
         if (dto.getTelemovel() != null) utente.setTelemovel(dto.getTelemovel());
         if (dto.getGenero() != null) utente.setGenero(dto.getGenero());
         if (dto.getNacionalidade() != null) utente.setNacionalidade(dto.getNacionalidade());

         // Salvar a entidade atualizada
         var utenteUpdated = utenteRepository.save(utente);
         UtenteResponseDTO responseDTO = utenteMapper.toUtenteResponseDTO(utenteUpdated);

         LOGGER.info("Utente com ID {} atualizado com sucesso", command.getId());

         return ResponseEntity.ok(responseDTO);
      } catch (Exception ex) {
         LOGGER.error("Erro ao atualizar utente com ID {}: {}", command.getId(), ex.getMessage(), ex);
         throw IgrpResponseStatusException.badRequest("Erro ao atualizar utente");
      }
   }

   

}
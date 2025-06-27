package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.models.UtenteServico;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteRepository;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteServicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import cv.igrp.simple.utente.application.commands.commands.AdicionarServicoUtenteCommand;

import cv.igrp.simple.utente.application.dto.ServicoAssociadoResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class AdicionarServicoUtenteCommandHandler implements CommandHandler<AdicionarServicoUtenteCommand, ResponseEntity<ServicoAssociadoResponseDTO>> {

   private final UtenteService utenteService;
   private final UtenteRepository utenteRepository;

   private final UtenteServicoRepository utenteServicoRepository;

   public AdicionarServicoUtenteCommandHandler(UtenteService utenteService, UtenteRepository utenteRepository, UtenteServicoRepository utenteServicoRepository) {

       this.utenteService = utenteService;
       this.utenteRepository = utenteRepository;
       this.utenteServicoRepository = utenteServicoRepository;
   }

   @Transactional
   @IgrpCommandHandler
   public ResponseEntity<ServicoAssociadoResponseDTO> handle(AdicionarServicoUtenteCommand command) {
      // TODO: Implement the command handling logic here
     int idUtente = command.getUtenteId();
      Utente utente = utenteService.obterUtentePorId(idUtente);

      Integer idServico = command.getServicoId();
      String tipoServico = command.getAdicionarservico().getTipoServico();

      validarAssociacaoServico(utente, tipoServico, idServico);

      var novoUtenteServico = new UtenteServico();
      novoUtenteServico.setObjetoTipo(tipoServico);
      novoUtenteServico.setDescricao("Descrição do serviço");
      novoUtenteServico.setReferencia("Referência do serviço");
      novoUtenteServico.setObjetoId(idServico);
      novoUtenteServico.setDataInicio(LocalDate.now());
      novoUtenteServico.setDataFim(LocalDate.now().plusMonths(1));
      novoUtenteServico.setValor(BigDecimal.valueOf(100.00));
      novoUtenteServico.setUtenteId(utente);
      novoUtenteServico.setEstado(Estado.ATIVO);

      utente.getServicos().add(novoUtenteServico);
      utenteRepository.save(utente);

      ServicoAssociadoResponseDTO responseDTO = new ServicoAssociadoResponseDTO();
      responseDTO.setIdServico(idServico);
      responseDTO.setIdUtente(idUtente);

      return ResponseEntity.ok(responseDTO);

   }

   private void validarAssociacaoServico(Utente utente, String objetoTipo, Integer objetoId) {
      // Regra 1: mesmo utente não pode ter o mesmo serviço 2 vezes
      boolean jaAssociadoMesmoUtente = utente.getServicos().stream()
              .anyMatch(s -> s.getObjetoTipo().equals(objetoTipo) && s.getObjetoId().equals(objetoId));
      if (jaAssociadoMesmoUtente) {

         throw new IgrpResponseStatusException(new IgrpProblem<>(
                 HttpStatus.NOT_FOUND,
                 "Serviço já associado a este utente",
                 null));
      }
   }


}
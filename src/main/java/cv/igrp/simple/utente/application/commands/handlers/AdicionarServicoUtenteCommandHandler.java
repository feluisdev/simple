package cv.igrp.simple.utente.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.models.UtenteServico;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteRepository;
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

   public AdicionarServicoUtenteCommandHandler(UtenteService utenteService, UtenteRepository utenteRepository) {

       this.utenteService = utenteService;
       this.utenteRepository = utenteRepository;
   }

   @Transactional
   @IgrpCommandHandler
   public ResponseEntity<ServicoAssociadoResponseDTO> handle(AdicionarServicoUtenteCommand command) {
      // TODO: Implement the command handling logic here
      Integer idUtente = Integer.valueOf(command.getUtenteId());
      Utente utente = utenteService.obterUtentePorId(idUtente);

      Integer idServico = Integer.valueOf(command.getServicoId());
      String tipoServico = command.getAdicionarservico().getTipoServico();

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

//todo nao associar um serivico a um utente com mesmo objectID e tipo 2 vezes
      //tambem nao pode associar um servico com mesmo objectID e tipo a 2 utentes diferentes
      utente.getServicos().add(novoUtenteServico);
      utenteRepository.save(utente);

      ServicoAssociadoResponseDTO responseDTO = new ServicoAssociadoResponseDTO();
      responseDTO.setIdServico(idServico);
      responseDTO.setIdUtente(idUtente);

      return ResponseEntity.ok(responseDTO);

   }

}
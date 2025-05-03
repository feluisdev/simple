package cv.igrp.simple.utente.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.shared.domain.exceptions.IgrpProblem;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.utente.application.dto.ServicoResponseDTO;
import cv.igrp.simple.utente.application.mapper.ServicoMapper;
import cv.igrp.simple.utente.application.queries.queries.ObterDetalhesServicoQuery;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.models.UtenteServico;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteServicoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObterDetalhesServicoQueryHandler implements QueryHandler<ObterDetalhesServicoQuery, ResponseEntity<ServicoResponseDTO>>{

   private  final UtenteService utenteService;

   private final UtenteServicoRepository utenteServicoRepository;

   private final ServicoMapper utenteServicoMapper;

   private final EntityManager entityManager;

   public ObterDetalhesServicoQueryHandler(UtenteService utenteService, UtenteServicoRepository utenteServicoRepository, ServicoMapper utenteServicoMapper, EntityManager entityManager) {

       this.utenteService = utenteService;
       this.utenteServicoRepository = utenteServicoRepository;
       this.utenteServicoMapper = utenteServicoMapper;
       this.entityManager = entityManager;
   }

   @IgrpQueryHandler
   public ResponseEntity<ServicoResponseDTO> handle(ObterDetalhesServicoQuery query) {
      // TODO: Implement the query handling logic here
      Integer utenteId = Integer.parseInt(query.getUtenteId());
      Integer servicoId = Integer.parseInt(query.getServicoId());

      Utente utente = utenteService.obterUtentePorId(utenteId);

      //Optional<UtenteServico> optionalUtenteServico = utenteServicoRepository.findByIdAndUtenteId_Id(servicoId, utenteId);

      Optional<UtenteServico> optionalUtenteServico = utenteServicoRepository.buscarPorServicoEUtente(servicoId, utenteId);

      if (optionalUtenteServico.isEmpty()) {

         throw new IgrpResponseStatusException(new IgrpProblem<>(
                 HttpStatus.NOT_FOUND,
                 "Servico não encontrado com o ID: " + servicoId,
                 null));
      }

      UtenteServico utenteServico = optionalUtenteServico.get();;

      ServicoResponseDTO responseDTO = utenteServicoMapper.mapToDTO(utenteServico);

      return ResponseEntity.ok(responseDTO);

   }

}
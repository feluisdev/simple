package cv.igrp.simple.utente.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteServicoEntityRepository;
import cv.igrp.simple.utente.application.dto.ServicoResponseDTO;
import cv.igrp.simple.utente.infrastructure.mappers.ServicoMapper;
import cv.igrp.simple.utente.application.queries.queries.ObterDetalhesServicoQuery;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteServicoEntity;

import cv.igrp.simple.utente.domain.service.UtenteService;
import jakarta.persistence.EntityManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObterDetalhesServicoQueryHandler implements QueryHandler<ObterDetalhesServicoQuery, ResponseEntity<ServicoResponseDTO>>{

   private  final UtenteService utenteService;

   private final UtenteServicoEntityRepository utenteServicoRepository;


   private final ServicoMapper utenteServicoMapper;

   private final EntityManager entityManager;

   public ObterDetalhesServicoQueryHandler(UtenteService utenteService, UtenteServicoEntityRepository utenteServicoRepository, ServicoMapper utenteServicoMapper, EntityManager entityManager) {


       this.utenteService = utenteService;
       this.utenteServicoRepository = utenteServicoRepository;
       this.utenteServicoMapper = utenteServicoMapper;
       this.entityManager = entityManager;
   }

   @IgrpQueryHandler
   public ResponseEntity<ServicoResponseDTO> handle(ObterDetalhesServicoQuery query) {
      // TODO: Implement the query handling logic here
      Integer utenteId = query.getUtenteId();
      Integer servicoId = query.getServicoId();

      UtenteEntity utente = utenteService.obterUtentePorId(utenteId);

      //Optional<UtenteServico> optionalUtenteServico = utenteServicoRepository.findByIdAndUtenteId_Id(servicoId, utenteId);

      Optional<UtenteServicoEntity> optionalUtenteServico = utenteServicoRepository.buscarPorServicoEUtente(servicoId, utenteId);


      if (optionalUtenteServico.isEmpty()) {

         throw IgrpResponseStatusException.notFound("Servico não encontrado com o ID: " + servicoId);
      }

      UtenteServicoEntity utenteServico = optionalUtenteServico.get();;


      ServicoResponseDTO responseDTO = utenteServicoMapper.mapToDTO(utenteServico);

      return ResponseEntity.ok(responseDTO);

   }

}
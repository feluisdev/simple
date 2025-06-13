package cv.igrp.simple.utente.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.application.queries.queries.ObterUtenteQuery;
import cv.igrp.simple.utente.domain.models.UtenteEntity;
import cv.igrp.simple.utente.domain.models.UtenteServicoEntity;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ObterUtenteQueryHandler implements QueryHandler<ObterUtenteQuery, ResponseEntity<UtenteResponseDTO>>{

   private final UtenteMapper utenteMapper;

   private final UtenteService utenteService;


   public ObterUtenteQueryHandler(UtenteMapper utenteMapper, UtenteService utenteService) {
       this.utenteMapper = utenteMapper;
       this.utenteService = utenteService;
   }

   @IgrpQueryHandler
   public ResponseEntity<UtenteResponseDTO> handle(ObterUtenteQuery query) {
      // TODO: Implement the query handling logic here
     Integer idUtente = Integer.valueOf(query.getId());

     UtenteEntity utente = utenteService.obterUtentePorId(idUtente);

      UtenteResponseDTO responseDTO = utenteMapper.toUtenteResponseDTO(utente);

      return  ResponseEntity.ok(responseDTO);

   }

}
package cv.igrp.simple.utente.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.utente.application.dto.ServicoResponseDTO;
import cv.igrp.simple.utente.application.mapper.ServicoMapper;
import cv.igrp.simple.utente.application.queries.filters.FiltroUtenteServico;
import cv.igrp.simple.utente.application.queries.queries.ListaServicosUtenteQuery;
import cv.igrp.simple.utente.domain.models.UtenteEntity;
import cv.igrp.simple.utente.domain.models.UtenteServicoEntity;

import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteServicoEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListaServicosUtenteQueryHandler implements QueryHandler<ListaServicosUtenteQuery, ResponseEntity<Page<ServicoResponseDTO>>>{

   private  final UtenteService utenteService;

   private final UtenteServicoEntityRepository utenteServicoRepository;

   private final ServicoMapper servicoMapper;

   private final FiltroUtenteServico filtroUtenteServico;

   public ListaServicosUtenteQueryHandler(UtenteService utenteService, UtenteServicoEntityRepository utenteServicoRepository, ServicoMapper servicoMapper, FiltroUtenteServico filtroUtenteServico) {



       this.utenteService = utenteService;
       this.utenteServicoRepository = utenteServicoRepository;
       this.servicoMapper = servicoMapper;
       this.filtroUtenteServico = filtroUtenteServico;
   }

   @IgrpQueryHandler
   public ResponseEntity<Page<ServicoResponseDTO>> handle(ListaServicosUtenteQuery query) {
      // TODO: Implement the query handling logic here
      Integer utenteId = Integer.parseInt(query.getUtenteId());

      UtenteEntity utente = utenteService.obterUtentePorId(utenteId);

      Specification<UtenteServicoEntity> spec = filtroUtenteServico.aplicarFiltros(query);

      Page<UtenteServicoEntity> pageServicos = utenteServicoRepository.findAll(spec, query.getPageable());


      Page<ServicoResponseDTO> response = pageServicos.map(s ->
              servicoMapper.mapToDTO(s));

      return ResponseEntity.ok(response);

   }



}
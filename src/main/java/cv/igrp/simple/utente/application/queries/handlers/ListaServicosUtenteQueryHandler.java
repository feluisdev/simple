package cv.igrp.simple.utente.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.utente.application.dto.ServicoResponseDTO;
import cv.igrp.simple.utente.application.mapper.ServicoMapper;
import cv.igrp.simple.utente.application.queries.filters.FiltroUtenteServico;
import cv.igrp.simple.utente.application.queries.queries.ListaServicosUtenteQuery;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.models.UtenteServico;
import cv.igrp.simple.utente.domain.service.UtenteService;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteServicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListaServicosUtenteQueryHandler implements QueryHandler<ListaServicosUtenteQuery, ResponseEntity<Page<ServicoResponseDTO>>>{

   private  final UtenteService utenteService;

   private final UtenteServicoRepository utenteServicoRepository;

   private final ServicoMapper servicoMapper;

   private final FiltroUtenteServico filtroUtenteServico;

   public ListaServicosUtenteQueryHandler(UtenteService utenteService, UtenteServicoRepository utenteServicoRepository, ServicoMapper servicoMapper, FiltroUtenteServico filtroUtenteServico) {


       this.utenteService = utenteService;
       this.utenteServicoRepository = utenteServicoRepository;
       this.servicoMapper = servicoMapper;
       this.filtroUtenteServico = filtroUtenteServico;
   }

   @IgrpQueryHandler
   public ResponseEntity<Page<ServicoResponseDTO>> handle(ListaServicosUtenteQuery query) {
      // TODO: Implement the query handling logic here
      Integer utenteId = Integer.parseInt(query.getUtenteId());

      Utente utente = utenteService.obterUtentePorId(utenteId);

      Specification<UtenteServico> spec = filtroUtenteServico.aplicarFiltros(query);

      //Page<UtenteServico> pageServicos = utenteServicoRepository.findByUtenteId_Id(utenteId, query.getPageable());
      Page<UtenteServico> pageServicos = utenteServicoRepository.findAll(spec, query.getPageable());


      Page<ServicoResponseDTO> response = pageServicos.map(s ->
              servicoMapper.mapToDTO(s));

      return ResponseEntity.ok(response);

   }



}
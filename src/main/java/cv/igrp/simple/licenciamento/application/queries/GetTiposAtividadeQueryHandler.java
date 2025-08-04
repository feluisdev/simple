package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.filter.TipoAtividadeFilter;
import cv.igrp.simple.licenciamento.domain.models.TipoAtividade;
import cv.igrp.simple.licenciamento.domain.repository.TipoAtividadeRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.TipoAtividadeMapper;
import cv.igrp.simple.shared.application.constants.Estado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.WrapperListaTipoAtividadeDTO;

import java.util.List;

@Component
public class GetTiposAtividadeQueryHandler implements QueryHandler<GetTiposAtividadeQuery, ResponseEntity<WrapperListaTipoAtividadeDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetTiposAtividadeQueryHandler.class);

  private final TipoAtividadeRepository repository;
  private final TipoAtividadeMapper mapper;

  public GetTiposAtividadeQueryHandler(TipoAtividadeRepository repository, TipoAtividadeMapper mapper) {

      this.repository = repository;
      this.mapper = mapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaTipoAtividadeDTO> handle(GetTiposAtividadeQuery query) {

    var filter = TipoAtividadeFilter.builder()
             .codigo(query.getCodigo())
             .nome(query.getNome())
             .estado(query.getEstado() != null ? Estado.valueOf(query.getEstado()) : null)
             .pageNumber(Integer.parseInt(query.getPagina()))
             .pageSize(Integer.parseInt(query.getTamanho()))
             .build();

     List<TipoAtividade> tipos = repository.findAll(filter);

     var content = tipos.stream()
             .map(mapper::toDTO)
             .toList();

     var response = new WrapperListaTipoAtividadeDTO();
     response.setContent(content);
     response.setPageNumber(filter.getPageNumber());
     response.setPageSize(filter.getPageSize());
     response.setTotalElements((long) content.size());

     return ResponseEntity.ok(response);

   }

}
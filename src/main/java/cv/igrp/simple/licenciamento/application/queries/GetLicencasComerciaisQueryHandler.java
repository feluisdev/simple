package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.filter.LicencaComercialFilter;
import cv.igrp.simple.licenciamento.domain.models.LicencaComercial;
import cv.igrp.simple.licenciamento.domain.repository.LicencaComercialRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.LicencaComercialMapper;
import cv.igrp.simple.shared.application.constants.EstadoLicenca;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.WrapperListaLicencaComercialDTO;

import java.util.List;

@Component
public class GetLicencasComerciaisQueryHandler implements QueryHandler<GetLicencasComerciaisQuery, ResponseEntity<WrapperListaLicencaComercialDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetLicencasComerciaisQueryHandler.class);


  private final LicencaComercialRepository repository;
  private final LicencaComercialMapper mapper;

  public GetLicencasComerciaisQueryHandler(LicencaComercialRepository repository, LicencaComercialMapper mapper) {

      this.repository = repository;
      this.mapper = mapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaLicencaComercialDTO> handle(GetLicencasComerciaisQuery query) {

     var filter = LicencaComercialFilter.builder()
             .alvara(query.getAlvara())
             .estadoLicenca(query.getEstadoLicenca() != null ? EstadoLicenca.valueOf(query.getEstadoLicenca()) : null)
             .idEstabalecimento(query.getIdEstabelecimento() != null ? Identificador.from(query.getIdEstabelecimento()) : null)
             .pageNumber(Integer.parseInt(query.getPagina()))
             .pageSize(Integer.parseInt(query.getTamanho()))
             .build();

     List<LicencaComercial> licencas = repository.findAll(filter);

     var content = licencas.stream()
             .map(mapper::toDTO)
             .toList();

     var response = new WrapperListaLicencaComercialDTO();
     response.setContent(content);
     response.setPageNumber(filter.getPageNumber());
     response.setPageSize(filter.getPageSize());
     response.setTotalElements((long) content.size());

     return ResponseEntity.ok(response);
  }

}
package cv.igrp.simple.licenciamento.application.queries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.WrapperListCategoryDTO;

@Component
public class GetListCategoriasLicenciamentoQueryHandler implements QueryHandler<GetListCategoriasLicenciamentoQuery, ResponseEntity<WrapperListCategoryDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetListCategoriasLicenciamentoQueryHandler.class);


  public GetListCategoriasLicenciamentoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListCategoryDTO> handle(GetListCategoriasLicenciamentoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
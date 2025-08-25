package cv.igrp.simple.licenciamento.application.queries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.CategoryResponseDTO;

@Component
public class GetCategoryLicenciamentoByIdQueryHandler implements QueryHandler<GetCategoryLicenciamentoByIdQuery, ResponseEntity<CategoryResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoryLicenciamentoByIdQueryHandler.class);


  public GetCategoryLicenciamentoByIdQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<CategoryResponseDTO> handle(GetCategoryLicenciamentoByIdQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.application.dto.WrapperListaEstabelecimentoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseDTO;

@Component
public class GetEstabelecimentosQueryHandler implements QueryHandler<GetEstabelecimentosQuery, ResponseEntity<WrapperListaEstabelecimentoDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEstabelecimentosQueryHandler.class);


  public GetEstabelecimentosQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaEstabelecimentoDTO> handle(GetEstabelecimentosQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
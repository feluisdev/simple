package cv.igrp.simple.configuracoes.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;

@Component
public class GetCategoriaServicoQueryHandler implements QueryHandler<GetCategoriaServicoQuery, ResponseEntity<CategoriasServicosResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoriaServicoQueryHandler.class);


  public GetCategoriaServicoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<CategoriasServicosResponseDTO> handle(GetCategoriaServicoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
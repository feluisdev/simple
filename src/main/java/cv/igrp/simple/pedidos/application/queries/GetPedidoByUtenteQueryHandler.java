package cv.igrp.simple.pedidos.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;

@Component
public class GetPedidoByUtenteQueryHandler implements QueryHandler<GetPedidoByUtenteQuery, ResponseEntity<PedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetPedidoByUtenteQueryHandler.class);


  public GetPedidoByUtenteQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<PedidoResponseDTO> handle(GetPedidoByUtenteQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
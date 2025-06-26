package cv.igrp.simple.pedidos.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;

@Component
public class GetPagamentoPedidoByIdQueryHandler implements QueryHandler<GetPagamentoPedidoByIdQuery, ResponseEntity<PagamentoPedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetPagamentoPedidoByIdQueryHandler.class);


  public GetPagamentoPedidoByIdQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<PagamentoPedidoResponseDTO> handle(GetPagamentoPedidoByIdQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
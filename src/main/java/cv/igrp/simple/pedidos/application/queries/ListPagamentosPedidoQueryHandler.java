package cv.igrp.simple.pedidos.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;

@Component
public class ListPagamentosPedidoQueryHandler implements QueryHandler<ListPagamentosPedidoQuery, ResponseEntity<List<PagamentoPedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListPagamentosPedidoQueryHandler.class);


  public ListPagamentosPedidoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<List<PagamentoPedidoResponseDTO>> handle(ListPagamentosPedidoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
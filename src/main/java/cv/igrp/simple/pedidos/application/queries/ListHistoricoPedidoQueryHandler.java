package cv.igrp.simple.pedidos.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.HistoricoPedidoResponseDTO;

import java.util.List;

@Component
public class ListHistoricoPedidoQueryHandler implements QueryHandler<ListHistoricoPedidoQuery, ResponseEntity<List<HistoricoPedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListHistoricoPedidoQueryHandler.class);


  public ListHistoricoPedidoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<List<HistoricoPedidoResponseDTO>> handle(ListHistoricoPedidoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
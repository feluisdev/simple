package cv.igrp.simple.pedidos.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;

@Component
public class ListPedidoQueryHandler implements QueryHandler<ListPedidoQuery, ResponseEntity<List<PedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListPedidoQueryHandler.class);


  public ListPedidoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<List<PedidoResponseDTO>> handle(ListPedidoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
package cv.igrp.simple.configuracoes.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;

@Component
public class ListStatusPedidoQueryHandler implements QueryHandler<ListStatusPedidoQuery, ResponseEntity<List<StatusPedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListStatusPedidoQueryHandler.class);


  public ListStatusPedidoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<List<StatusPedidoResponseDTO>> handle(ListStatusPedidoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
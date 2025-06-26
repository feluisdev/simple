package cv.igrp.simple.pedidos.application.queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;

@Component
public class GetAvaliacaoPedidoQueryHandler implements QueryHandler<GetAvaliacaoPedidoQuery, ResponseEntity<AvaliacaoPedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetAvaliacaoPedidoQueryHandler.class);


  public GetAvaliacaoPedidoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<AvaliacaoPedidoResponseDTO> handle(GetAvaliacaoPedidoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
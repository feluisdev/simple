package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.pedidos.application.dto.WrapperListaPedidoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ListPedidoQueryHandler implements QueryHandler<ListPedidoQuery, ResponseEntity<WrapperListaPedidoDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListPedidoQueryHandler.class);


  public ListPedidoQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaPedidoDTO> handle(ListPedidoQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
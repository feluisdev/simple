package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;

import java.util.List;

@Component
public class GetPedidoByUtenteQueryHandler implements QueryHandler<GetPedidoByUtenteQuery, ResponseEntity<List<PedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetPedidoByUtenteQueryHandler.class);

  private final PedidoRepository pedidoRepository;
  private final PedidoMapper pedidoMapper;

  public GetPedidoByUtenteQueryHandler(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {

      this.pedidoRepository = pedidoRepository;
      this.pedidoMapper = pedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<PedidoResponseDTO>> handle(GetPedidoByUtenteQuery query) {
     var id = Integer.parseInt(query.getUtenteId());
     var pedidos = pedidoRepository.findAllByUtenteId(id);

     var dtos = pedidos.stream()
             .map(pedidoMapper::toPedidoResponseDTO)
             .toList();

     return ResponseEntity.ok(dtos);
  }

}
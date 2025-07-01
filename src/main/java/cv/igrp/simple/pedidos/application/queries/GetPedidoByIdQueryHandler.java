package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;

@Component
public class GetPedidoByIdQueryHandler implements QueryHandler<GetPedidoByIdQuery, ResponseEntity<PedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetPedidoByIdQueryHandler.class);

  private final PedidoRepository pedidoRepository;
  private final PedidoMapper pedidoMapper;

  public GetPedidoByIdQueryHandler(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {

      this.pedidoRepository = pedidoRepository;
      this.pedidoMapper = pedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<PedidoResponseDTO> handle(GetPedidoByIdQuery query) {
     var identificador= Identificador.from(query.getPedidoId());
     var pedido = pedidoRepository.findById(identificador)
             .orElseThrow(() -> IgrpResponseStatusException.notFound("Pedido not found"));

     return ResponseEntity.ok(pedidoMapper.toPedidoResponseDTO(pedido));
  }

}
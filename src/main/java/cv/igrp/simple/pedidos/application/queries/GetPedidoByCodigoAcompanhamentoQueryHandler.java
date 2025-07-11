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

@Component
public class GetPedidoByCodigoAcompanhamentoQueryHandler implements QueryHandler<GetPedidoByCodigoAcompanhamentoQuery, ResponseEntity<PedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetPedidoByCodigoAcompanhamentoQueryHandler.class);


  private final PedidoMapper pedidoMapper;

  private final PedidoRepository pedidoRepository;

  public GetPedidoByCodigoAcompanhamentoQueryHandler(PedidoMapper pedidoMapper, PedidoRepository pedidoRepository) {

      this.pedidoMapper = pedidoMapper;
      this.pedidoRepository = pedidoRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<PedidoResponseDTO> handle(GetPedidoByCodigoAcompanhamentoQuery query) {
     var codigoAcompanhamento= CodigoAcompanhamento.of(query.getCodigoAcompanhamento());
     var pedido = pedidoRepository.findByCodigoAcompanhamento(codigoAcompanhamento)
             .orElseThrow(() -> IgrpResponseStatusException.notFound("Pedido not found"));

     return ResponseEntity.ok(pedidoMapper.toPedidoResponseDTO(pedido));
  }

}
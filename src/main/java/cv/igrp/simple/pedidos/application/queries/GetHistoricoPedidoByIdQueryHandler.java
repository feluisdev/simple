package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.AvaliacaoRepository;
import cv.igrp.simple.pedidos.domain.repository.HistoricoPedidoRepository;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.HistoricoPedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.HistoricoPedidoResponseDTO;

@Component
public class GetHistoricoPedidoByIdQueryHandler implements QueryHandler<GetHistoricoPedidoByIdQuery, ResponseEntity<HistoricoPedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetHistoricoPedidoByIdQueryHandler.class);

  private final PedidoRepository pedidoRepository;
  private final HistoricoPedidoRepository historicoPedidoRepository;
  private final HistoricoPedidoMapper historicoPedidoMapper;

  public GetHistoricoPedidoByIdQueryHandler(PedidoRepository pedidoRepository, HistoricoPedidoRepository historicoPedidoRepository, HistoricoPedidoMapper historicoPedidoMapper) {

      this.pedidoRepository = pedidoRepository;
      this.historicoPedidoRepository = historicoPedidoRepository;
      this.historicoPedidoMapper = historicoPedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<HistoricoPedidoResponseDTO> handle(GetHistoricoPedidoByIdQuery query) {

      var pedidoId = Identificador.from(query.getPedidoId());
       var historicoId = Identificador.from(query.getHistoricoId());

       var historicoPedido = historicoPedidoRepository.findByPedidoIdAndHistoricoId(pedidoId, historicoId)
               .orElseThrow(() -> IgrpResponseStatusException.notFound("Historico Pedido not found for the given IDs"));

       var responseDTO = historicoPedidoMapper.toResponseDTO(historicoPedido);

       return ResponseEntity.ok(responseDTO);

   }

}
package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.simple.pedidos.domain.repository.HistoricoPedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.HistoricoPedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
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
public class ListHistoricoPedidoQueryHandler implements QueryHandler<ListHistoricoPedidoQuery, ResponseEntity<List<HistoricoPedidoResponseDTO>>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListHistoricoPedidoQueryHandler.class);

    private final HistoricoPedidoMapper historicoPedidoMapper;
    private final HistoricoPedidoRepository historicoPedidoRepository;

    public ListHistoricoPedidoQueryHandler(HistoricoPedidoMapper historicoPedidoMapper, HistoricoPedidoRepository historicoPedidoRepository) {

        this.historicoPedidoMapper = historicoPedidoMapper;
        this.historicoPedidoRepository = historicoPedidoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<List<HistoricoPedidoResponseDTO>> handle(ListHistoricoPedidoQuery query) {
      var pedidoId = Identificador.from(query.getPedidoId());

      var historicos = historicoPedidoRepository.findHistoricosByPedido(pedidoId);

      List<HistoricoPedidoResponseDTO> responseDto = historicos.stream()
                .map(historicoPedidoMapper::toResponseDTO)
                .toList();

      return ResponseEntity.ok(responseDto);
    }

}
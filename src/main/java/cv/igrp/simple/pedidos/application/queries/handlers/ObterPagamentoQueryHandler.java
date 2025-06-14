package cv.igrp.simple.pedidos.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.mapper.PagamentosPedidosMapper;
import cv.igrp.simple.pedidos.application.queries.queries.ObterPagamentoQuery;
import cv.igrp.simple.pedidos.domain.models.PagamentosPedidosEntity;
import cv.igrp.simple.pedidos.domain.service.PagamentosPedidosService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ObterPagamentoQueryHandler implements QueryHandler<ObterPagamentoQuery, ResponseEntity<PagamentoPedidoResponseDTO>> {

    private final PagamentosPedidosService pagamentosPedidosService;
    private final PagamentosPedidosMapper pagamentosPedidosMapper;

    public ObterPagamentoQueryHandler(PagamentosPedidosService pagamentosPedidosService,
                                     PagamentosPedidosMapper pagamentosPedidosMapper) {
        this.pagamentosPedidosService = pagamentosPedidosService;
        this.pagamentosPedidosMapper = pagamentosPedidosMapper;
    }

    @IgrpQueryHandler
    public ResponseEntity<PagamentoPedidoResponseDTO> handle(ObterPagamentoQuery query) {
        PagamentosPedidosEntity pagamento = pagamentosPedidosService.obterPagamentoPorId(query.getId());
        PagamentoPedidoResponseDTO responseDTO = pagamentosPedidosMapper.toPagamentoPedidoResponseDTO(pagamento);
        
        return ResponseEntity.ok(responseDTO);
    }
}
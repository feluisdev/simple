package cv.igrp.simple.pedidos.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.mapper.AvaliacoesPedidosMapper;
import cv.igrp.simple.pedidos.application.queries.queries.ObterAvaliacaoPedidoQuery;
import cv.igrp.simple.pedidos.domain.models.AvaliacoesPedidosEntity;
import cv.igrp.simple.pedidos.domain.service.AvaliacoesPedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ObterAvaliacaoPedidoQueryHandler implements QueryHandler<ObterAvaliacaoPedidoQuery, ResponseEntity<AvaliacaoPedidoResponseDTO>> {

    private final AvaliacoesPedidosService avaliacoesPedidosService;
    private final AvaliacoesPedidosMapper avaliacoesPedidosMapper;

    public ObterAvaliacaoPedidoQueryHandler(AvaliacoesPedidosService avaliacoesPedidosService,
                                           AvaliacoesPedidosMapper avaliacoesPedidosMapper) {
        this.avaliacoesPedidosService = avaliacoesPedidosService;
        this.avaliacoesPedidosMapper = avaliacoesPedidosMapper;
    }

    @Override
    public ResponseEntity<AvaliacaoPedidoResponseDTO> handle(ObterAvaliacaoPedidoQuery query) {
        // Converter UUID para Integer para buscar no repositório
        Integer pedidoIdInt = query.getPedidoId().hashCode() & Integer.MAX_VALUE;
        
        // Buscar a avaliação pelo ID do pedido
        AvaliacoesPedidosEntity avaliacao = avaliacoesPedidosService.obterAvaliacaoPorPedidoId(pedidoIdInt);
        
        // Converter a entidade para DTO
        AvaliacaoPedidoResponseDTO responseDTO = avaliacoesPedidosMapper.toAvaliacaoPedidoResponseDTO(avaliacao);
        
        // Retornar o DTO
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
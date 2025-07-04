package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.simple.pedidos.domain.repository.AvaliacaoRepository;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;

import java.util.List;

@Component
public class GetAvaliacaoPedidoQueryHandler implements QueryHandler<GetAvaliacaoPedidoQuery, ResponseEntity<AvaliacaoPedidoResponseDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAvaliacaoPedidoQueryHandler.class);

    private final PedidoRepository pedidoRepository;
    private final AvaliacaoRepository avaliacaoRepository;

    public GetAvaliacaoPedidoQueryHandler(PedidoRepository pedidoRepository, AvaliacaoRepository avaliacaoRepository) {

        this.pedidoRepository = pedidoRepository;
        this.avaliacaoRepository = avaliacaoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<AvaliacaoPedidoResponseDTO> handle(GetAvaliacaoPedidoQuery query) {


        var pedidoId = Identificador.from(query.getPedidoId());
        var avaliacaoId = Identificador.from(query.getAvaliacaoId());


        var avaliacao = avaliacaoRepository.findByPedidoIdAndAvaliacaoID(pedidoId, avaliacaoId).orElseThrow(
                () -> IgrpResponseStatusException.notFound("avaliacao nao encontrada para esse pedido")
        );


        var responseDTO = new AvaliacaoPedidoResponseDTO(
                avaliacao.getAvaliacaoUuid().getStringValor(),
                avaliacao.getPedido().getPedidoUuid().getStringValor(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao(),
                avaliacao.getUserId(),
                "nome" // todo resolve this
        );


        return ResponseEntity.ok(responseDTO);

    }

}
package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.AvaliacaoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.AvaliacaoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;

@Component
public class GetListAvaliacoesPedidoQueryHandler implements QueryHandler<GetListAvaliacoesPedidoQuery, ResponseEntity<List<AvaliacaoPedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetListAvaliacoesPedidoQueryHandler.class);

  private final AvaliacaoRepository avaliacaoRepository;

    private final AvaliacaoMapper avaliacaoMapper;

  public GetListAvaliacoesPedidoQueryHandler(AvaliacaoRepository avaliacaoRepository, AvaliacaoMapper avaliacaoMapper) {

      this.avaliacaoRepository = avaliacaoRepository;
      this.avaliacaoMapper = avaliacaoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<AvaliacaoPedidoResponseDTO>> handle(GetListAvaliacoesPedidoQuery query) {

     var pedidoId = Identificador.from(query.getPedidoId());

    var avaliacoes = avaliacaoRepository.findAvaliacoesByPedido(pedidoId);

     List<AvaliacaoPedidoResponseDTO> responseDTO = avaliacoes.stream()
                .map(avaliacaoMapper::toResponseDTO)
                .toList();

    return ResponseEntity.ok(responseDTO);
  }

}
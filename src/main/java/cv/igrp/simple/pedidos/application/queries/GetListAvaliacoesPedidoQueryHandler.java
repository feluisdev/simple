package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.AvaliacaoRepository;
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


  public GetListAvaliacoesPedidoQueryHandler(AvaliacaoRepository avaliacaoRepository) {

      this.avaliacaoRepository = avaliacaoRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<AvaliacaoPedidoResponseDTO>> handle(GetListAvaliacoesPedidoQuery query) {
    // TODO: Implement the query handling logic here

     var avaliacaoId = Identificador.from(query.getPedidoId());

    var avaliacoes = avaliacaoRepository.findAvaliacoesByPedido(avaliacaoId);

     List<AvaliacaoPedidoResponseDTO> responseDTO = avaliacoes.stream()
                .map(avaliacao -> new AvaliacaoPedidoResponseDTO(
                        avaliacao.getAvaliacaoUuid().getStringValor(),
                        avaliacao.getPedido().getPedidoUuid().getStringValor(),
                        avaliacao.getNota(),
                        avaliacao.getComentario(),
                        avaliacao.getDataAvaliacao(),
                        avaliacao.getUserId(),
                        "nome" // todo resolve this
                ))
                .toList();

    return ResponseEntity.ok(responseDTO);
  }

}
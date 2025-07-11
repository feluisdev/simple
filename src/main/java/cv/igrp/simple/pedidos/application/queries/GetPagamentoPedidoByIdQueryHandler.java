package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.PagamentoPedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.PagamentoPedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;

@Component
public class GetPagamentoPedidoByIdQueryHandler implements QueryHandler<GetPagamentoPedidoByIdQuery, ResponseEntity<PagamentoPedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetPagamentoPedidoByIdQueryHandler.class);

  private final PagamentoPedidoRepository pagamentoRepository;
  private final PagamentoPedidoMapper pagamentoMapper;

  public GetPagamentoPedidoByIdQueryHandler(PagamentoPedidoRepository pagamentoRepository, PagamentoPedidoMapper pagamentoMapper) {

      this.pagamentoRepository = pagamentoRepository;
      this.pagamentoMapper = pagamentoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<PagamentoPedidoResponseDTO> handle(GetPagamentoPedidoByIdQuery query) {
     var pedidoId = Identificador.from(query.getPedidoId());
     var pagamentoId = Identificador.from(query.getPagamentoId());

     var pagamento = pagamentoRepository.findByPedidoIdAndPagamentoId(pedidoId, pagamentoId)
             .orElseThrow(() -> IgrpResponseStatusException.notFound("Pagamento não encontrado para o pedido informado."));


     return ResponseEntity.ok(pagamentoMapper.toResponseDTO(pagamento));
  }

}
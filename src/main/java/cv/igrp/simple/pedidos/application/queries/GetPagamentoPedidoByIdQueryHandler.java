package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.PagamentoPedidoRepository;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
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

  private final PagamentoPedidoMapper pagamentoMapper;

  private final PedidoRepository pedidoRepository;

  public GetPagamentoPedidoByIdQueryHandler(PagamentoPedidoMapper pagamentoMapper, PedidoRepository pedidoRepository) {

      this.pagamentoMapper = pagamentoMapper;
      this.pedidoRepository = pedidoRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<PagamentoPedidoResponseDTO> handle(GetPagamentoPedidoByIdQuery query) {
     var pedidoId = Identificador.from(query.getPedidoId());

       var pedido = pedidoRepository.findById(pedidoId)
               .orElseThrow(() ->  IgrpResponseStatusException.notFound("Pedido não encontrado: " + pedidoId));

      // System.out.println("pedido: "+pedido);

       if(pedido.getPagamento()==null){
          throw  IgrpResponseStatusException.notFound("Nao Foi registado nenhum pagamento para esse pedido");
       }
       //System.out.println("pagamento: "+pedido.getPagamento());
     return ResponseEntity.ok(pagamentoMapper.toResponseDTO(pedido.getPagamento()));
  }

}
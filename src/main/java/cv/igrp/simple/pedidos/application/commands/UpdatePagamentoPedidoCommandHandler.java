package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.domain.models.Pagamento;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.PagamentoPedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;

@Component
public class UpdatePagamentoPedidoCommandHandler implements CommandHandler<UpdatePagamentoPedidoCommand, ResponseEntity<PagamentoPedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePagamentoPedidoCommandHandler.class);

   private final PedidoRepository pedidoRepository;
   private final PagamentoPedidoMapper pagamentoPedidoMapper;
   public UpdatePagamentoPedidoCommandHandler(PedidoRepository pedidoRepository, PagamentoPedidoMapper pagamentoPedidoMapper) {

       this.pedidoRepository = pedidoRepository;
       this.pagamentoPedidoMapper = pagamentoPedidoMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<PagamentoPedidoResponseDTO> handle(UpdatePagamentoPedidoCommand command) {
      var pedidoId = Identificador.from(command.getPedidoId());

      var dto = command.getCreatepagamentopedido();

      var pedido = pedidoRepository.findById(pedidoId)
              .orElseThrow(() ->  IgrpResponseStatusException.notFound("Pedido não encontrado: " + command.getPedidoId()));

      Pagamento pagamento = pedido.getPagamento();

      if (pagamento == null) {
         throw  IgrpResponseStatusException.notFound("Pagamento não encontrado para este pedido.");
      }

      var pagamentoUuid = Identificador.from(command.getPagamentoId());
      if (!pagamento.getPagamentoUuid().equals(pagamentoUuid)) {
         throw IgrpResponseStatusException.notFound("Pagamento informado não pertence ao pedido.");
      }

      pagamento.atualizar(
              dto.getMetodoPagamento(),
              dto.getReferenciaPagamento(),
              dto.getObservacao(),
              dto.getValor()
      );

      pedidoRepository.save(pedido);

      return ResponseEntity.ok(pagamentoPedidoMapper.toResponseDTO(pagamento));
   }

}
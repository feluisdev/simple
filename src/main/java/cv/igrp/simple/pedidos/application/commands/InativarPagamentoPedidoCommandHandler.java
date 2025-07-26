package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.PagamentoPedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class InativarPagamentoPedidoCommandHandler implements CommandHandler<InativarPagamentoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(InativarPagamentoPedidoCommandHandler.class);

   private final PagamentoPedidoMapper pagamentoMapper;

   private final PedidoRepository pedidoRepository;

   public InativarPagamentoPedidoCommandHandler(PagamentoPedidoMapper pagamentoMapper, PedidoRepository pedidoRepository) {

       this.pagamentoMapper = pagamentoMapper;
       this.pedidoRepository = pedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(InativarPagamentoPedidoCommand command) {
      var pedidoId = Identificador.from(command.getPedidoId());

      var pedido = pedidoRepository.findById(pedidoId)
              .orElseThrow(() ->  IgrpResponseStatusException.notFound("Pedido não encontrado: " + pedidoId));

      if(pedido.getPagamento()==null){
         throw  IgrpResponseStatusException.notFound("Nao Foi registado nenhum pagamento para esse pedido");
      }

      pedido.cancelarPagamento();

      pedidoRepository.save(pedido);

      return ResponseEntity.ok(Map.of("pagamento cancelado", pedido.getPagamento().getPagamentoUuid().getStringValor()));
   }

}
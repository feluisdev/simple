package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class RegistarPagamentoPedidoCommandHandler implements CommandHandler<RegistarPagamentoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(RegistarPagamentoPedidoCommandHandler.class);

   private final PedidoRepository pedidoRepository;

   public RegistarPagamentoPedidoCommandHandler(PedidoRepository pedidoRepository) {

       this.pedidoRepository = pedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(RegistarPagamentoPedidoCommand command) {
      var pedidoId = Identificador.from(command.getPedidoId());

      var dto = command.getCreatepagamentopedido();

      var pedido = pedidoRepository.findById(pedidoId)
              .orElseThrow(() ->  IgrpResponseStatusException.notFound("Pedido não encontrado: " + command.getPedidoId()));

      pedido.registrarPagamento(
              dto.getMetodoPagamento(),
              dto.getReferenciaPagamento(),
              dto.getObservacao(),
              dto.getValor()
      );

      pedidoRepository.save(pedido);

      Map<String, Object> body = Map.of(
              "mensagem", "Pagamento registrado com sucesso.",
              "pagamentoId", pedido.getPagamento().getPagamentoUuid().getValor().toString()
      );

      return ResponseEntity.ok(body);
   }

}
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
public class AddAvaliacaoPedidoCommandHandler implements CommandHandler<AddAvaliacaoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(AddAvaliacaoPedidoCommandHandler.class);

   private final PedidoRepository pedidoRepository;

   public AddAvaliacaoPedidoCommandHandler(PedidoRepository pedidoRepository) {

       this.pedidoRepository = pedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(AddAvaliacaoPedidoCommand command) {

      var pedidoId = Identificador.from(command.getPedidoId());
      var dto = command.getCreateavaliacaopedido();

      var pedido = pedidoRepository.findById(pedidoId).orElseThrow(
              () ->  IgrpResponseStatusException.notFound("Pedido nao encontrado")
      );

      pedido.avaliarPedido(dto.getComentario(), dto.getNota());

      pedidoRepository.save(pedido);

      return ResponseEntity.ok(Map.of("sucesso", "avaliacao adicionado com sucesso"));
   }

}
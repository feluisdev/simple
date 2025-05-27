package cv.igrp.simple.pedido.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedido.domain.models.StatusPedido;
import cv.igrp.simple.pedido.domain.repository.StatusPedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class CreateStatusPedidoCommandHandler implements CommandHandler<CreateStatusPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateStatusPedidoCommandHandler.class);

   private final StatusPedidoRepository statusPedidoRepository;

   public CreateStatusPedidoCommandHandler(StatusPedidoRepository statusPedidoRepository) {

       this.statusPedidoRepository = statusPedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(CreateStatusPedidoCommand command) {
      // TODO: Implement the command handling logic here

      var dto = command.getStatuspedidorequest();
      StatusPedido statusPedido = new StatusPedido(
              dto.getCodigo(),
              dto.getNome(),
              dto.getDescricao(),
              dto.getCor(),
              dto.getIcone(),
              dto.getOrdem(),
              dto.isVisivelPortal()
      );

      statusPedidoRepository.save(statusPedido);

      var response = Map.of(
              "mensagem",
              "categoria criado com sucesso"
      );

      return ResponseEntity.status(201).body(response);
   }

}
package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddDocumentoPedidoCommandHandler implements CommandHandler<AddDocumentoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(AddDocumentoPedidoCommandHandler.class);

   private final PedidoRepository pedidoRepository;


   public AddDocumentoPedidoCommandHandler(PedidoRepository pedidoRepository) {

       this.pedidoRepository = pedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(AddDocumentoPedidoCommand command) {

      var pedidoId = Identificador.from(command.getPedidoId());
      var dto = command.getUploaddocumentopedido();

      var pedido = pedidoRepository.findById(pedidoId)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Pedido não encontrado para o ID fornecido."));

      pedido.adicionarDocumento(
              dto.getNome(),
              dto.getDescricao(),
              dto.getTipoDocumento(),
              dto.getUrl()
      );

     pedidoRepository.save(pedido);

      return ResponseEntity.ok(
              Map.of(
                      "message", "Documento adicionado com sucesso ao pedido."
              )
      );

   }

}
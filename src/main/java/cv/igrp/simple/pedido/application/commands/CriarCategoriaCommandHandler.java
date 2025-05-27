package cv.igrp.simple.pedido.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedido.domain.models.CategoriaPedido;
import cv.igrp.simple.pedido.domain.repository.CategoriaPedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class CriarCategoriaCommandHandler implements CommandHandler<CriarCategoriaCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CriarCategoriaCommandHandler.class);

   private final CategoriaPedidoRepository categoriaRepository;

   public CriarCategoriaCommandHandler(CategoriaPedidoRepository categoriaRepository) {

       this.categoriaRepository = categoriaRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(CriarCategoriaCommand command) {
      // TODO: Implement the command handling logic here
      var dto = command.getCategoriarequest();

      CategoriaPedido categoria = new CategoriaPedido(
              null,
              dto.getNome(),
              dto.getDescricao(),
              dto.getIcone(),
              dto.getCor(),
              dto.getOrdem(),
              true
      );

      categoriaRepository.save(categoria);

      var response = Map.of(
              "mensagem",
                  "categoria criado com sucesso"
      );

      return ResponseEntity.status(201).body(response);
   }

}
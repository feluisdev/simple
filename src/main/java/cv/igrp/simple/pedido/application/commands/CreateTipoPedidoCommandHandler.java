package cv.igrp.simple.pedido.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedido.domain.models.CategoriaPedido;
import cv.igrp.simple.pedido.domain.models.TipoPedido;
import cv.igrp.simple.pedido.domain.repository.CategoriaPedidoRepository;
import cv.igrp.simple.pedido.domain.repository.TipoPedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class CreateTipoPedidoCommandHandler implements CommandHandler<CreateTipoPedidoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateTipoPedidoCommandHandler.class);

   private final TipoPedidoRepository tipoPedidoRepository;

   private final CategoriaPedidoRepository categoriaPedidoRepository;
   public CreateTipoPedidoCommandHandler(TipoPedidoRepository tipoPedidoRepository, CategoriaPedidoRepository categoriaPedidoRepository) {

       this.tipoPedidoRepository = tipoPedidoRepository;
       this.categoriaPedidoRepository = categoriaPedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(CreateTipoPedidoCommand command) {
      // TODO: Implement the command handling logic here
    var dto = command.getTipopedidorequest();

      CategoriaPedido categoriaPedido = categoriaPedidoRepository.getById(dto.getCategoriaId());

    TipoPedido tipoPedido = new TipoPedido(
              dto.getCodigo(),
              dto.getNome(),
              dto.getDescricao(),
              dto.getPrazoEstimado(),
              dto.getValorBase(),
              dto.isRequerVistoria(),
              dto.isRequerAnaliseTecnica(),
              dto.isRequerAprovacao(),
              dto.isDisponivelPortal(),
               true,
              categoriaPedido
      );


     tipoPedidoRepository.save(tipoPedido);

      var response = Map.of(
              "mensagem",
              "Tipo pedido criado com sucesso"
      );

      return ResponseEntity.status(201).body(response);
   }

}
package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;

@Component
public class UpdateStatusCommandHandler implements CommandHandler<UpdateStatusCommand, ResponseEntity<PedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateStatusCommandHandler.class);

   private final PedidoRepository pedidoRepository;
   private final StatusPedidoRepository statusPedidoRepository;
   private final PedidoMapper pedidoMapper;

   public UpdateStatusCommandHandler(PedidoRepository pedidoRepository, StatusPedidoRepository statusPedidoRepository, PedidoMapper pedidoMapper) {

       this.pedidoRepository = pedidoRepository;
       this.statusPedidoRepository = statusPedidoRepository;
       this.pedidoMapper = pedidoMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<PedidoResponseDTO> handle(UpdateStatusCommand command) {
      var pedidoId = Identificador.from(command.getPedidoId());
      var statusId = Identificador.from(command.getStatusId());


      Pedido pedido = pedidoRepository.findById(pedidoId)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Pedido não encontrado."));

      StatusPedido novoStatus = statusPedidoRepository.getById(statusId)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Status não encontrado."));


      // Registrar novo status com histórico
      pedido.registarHistorico("Status atualizado via sistema", novoStatus);

      // Persistir
      Pedido atualizado = pedidoRepository.save(pedido);

      // Mapper
      PedidoResponseDTO response = pedidoMapper.toPedidoResponseDTO(atualizado);

      return ResponseEntity.ok(response);
   }

}
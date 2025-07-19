package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.pedidos.domain.models.Pagamento;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.pedidos.domain.repository.PagamentoPedidoRepository;
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

   private final PagamentoPedidoMapper pagamentoPedidoMapper;

   private final PedidoRepository pedidoRepository;

   private final StatusPedidoRepository statusPedidoRepository;

   public UpdatePagamentoPedidoCommandHandler(PagamentoPedidoMapper pagamentoPedidoMapper, PedidoRepository pedidoRepository, StatusPedidoRepository statusPedidoRepository) {

       this.pagamentoPedidoMapper = pagamentoPedidoMapper;
       this.pedidoRepository = pedidoRepository;
       this.statusPedidoRepository = statusPedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<PagamentoPedidoResponseDTO> handle(UpdatePagamentoPedidoCommand command) {
      var pedidoId = Identificador.from(command.getPedidoId());

      var pedido = pedidoRepository.findById(pedidoId).orElseThrow(
                () -> IgrpResponseStatusException.notFound("Pedido não encontrado.")
      );

       var statusPago = statusPedidoRepository.getByCodigo("PAGO").orElseThrow(
                () -> IgrpResponseStatusException.notFound("Status 'PAGO' não encontrado.")
        );

       if(pedido.getPagamento()==null){
           throw  IgrpResponseStatusException.notFound("Nao Foi registado nenhum pagamento para esse pedido");
       }

      pedido.confirmarPagamento(statusPago);

      pedidoRepository.save(pedido);

      var pagamentoAtualizado = pedido.getPagamento();

      return ResponseEntity.ok(pagamentoPedidoMapper.toResponseDTO(pagamentoAtualizado));
   }

}
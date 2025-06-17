package cv.igrp.simple.pedidos.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.simple.pedidos.application.commands.commands.RegistrarPagamentoPedidoCommand;
import cv.igrp.simple.pedidos.application.mapper.PagamentosPedidosMapper;
import cv.igrp.simple.pedidos.domain.models.PagamentosPedidosEntity;
import cv.igrp.simple.pedidos.domain.service.PagamentosPedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Handler para processar o comando de registro de pagamento de pedido
 */
@Component
public class RegistrarPagamentoPedidoCommandHandler implements CommandHandler<RegistrarPagamentoPedidoCommand, ResponseEntity<Integer>> {

    private final PagamentosPedidosService pagamentosPedidosService;
    private final PagamentosPedidosMapper pagamentosPedidosMapper;

    public RegistrarPagamentoPedidoCommandHandler(PagamentosPedidosService pagamentosPedidosService,
                                                PagamentosPedidosMapper pagamentosPedidosMapper) {
        this.pagamentosPedidosService = pagamentosPedidosService;
        this.pagamentosPedidosMapper = pagamentosPedidosMapper;
    }

    @Override
    public ResponseEntity<Integer> handle(RegistrarPagamentoPedidoCommand command) {
        // Verifica se o pedidoId no DTO corresponde ao pedidoId no comando
        if (!command.getCreatePagamentoPedidoDTO().getPedidoId().equals(command.getPedidoId())) {
            command.getCreatePagamentoPedidoDTO().setPedidoId(command.getPedidoId());
        }
        
        // Converte o DTO para entidade
        PagamentosPedidosEntity pagamento = pagamentosPedidosMapper.toEntity(command.getCreatePagamentoPedidoDTO());
        
        // Registra o pagamento
        PagamentosPedidosEntity pagamentoSalvo = pagamentosPedidosService.registrarPagamento(pagamento);
        
        // Retorna o ID do pagamento criado
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoSalvo.getId());
    }
}
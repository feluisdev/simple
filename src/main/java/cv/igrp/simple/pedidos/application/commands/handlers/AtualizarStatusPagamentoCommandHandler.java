package cv.igrp.simple.pedidos.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.application.commands.commands.AtualizarStatusPagamentoCommand;
import cv.igrp.simple.pedidos.application.mapper.PagamentosPedidosMapper;
import cv.igrp.simple.pedidos.domain.models.PagamentosPedidosEntity;
import cv.igrp.simple.pedidos.domain.service.PagamentosPedidosService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AtualizarStatusPagamentoCommandHandler implements CommandHandler<AtualizarStatusPagamentoCommand, ResponseEntity<Void>> {

    private final PagamentosPedidosService pagamentosPedidosService;
    private final PagamentosPedidosMapper pagamentosPedidosMapper;

    public AtualizarStatusPagamentoCommandHandler(@Lazy PagamentosPedidosService pagamentosPedidosService, 
                                                 PagamentosPedidosMapper pagamentosPedidosMapper) {
        this.pagamentosPedidosService = pagamentosPedidosService;
        this.pagamentosPedidosMapper = pagamentosPedidosMapper;
    }

    @IgrpCommandHandler
    public ResponseEntity<Void> handle(AtualizarStatusPagamentoCommand command) {
        PagamentosPedidosEntity pagamento = pagamentosPedidosService.obterPagamentoPorId(command.getId());
        pagamentosPedidosMapper.updateEntityFromDTO(command.getUpdateStatusPagamento(), pagamento);
        pagamentosPedidosService.atualizarStatusPagamento(command.getId(), pagamento);
        
        return ResponseEntity.noContent().build();
    }
}
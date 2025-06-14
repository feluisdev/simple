package cv.igrp.simple.pedidos.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.application.commands.commands.ExcluirPagamentoCommand;
import cv.igrp.simple.pedidos.domain.service.PagamentosPedidosService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ExcluirPagamentoCommandHandler implements CommandHandler<ExcluirPagamentoCommand, ResponseEntity<Void>> {

    private final PagamentosPedidosService pagamentosPedidosService;

    public ExcluirPagamentoCommandHandler(PagamentosPedidosService pagamentosPedidosService) {
        this.pagamentosPedidosService = pagamentosPedidosService;
    }

    @IgrpCommandHandler
    public ResponseEntity<Void> handle(ExcluirPagamentoCommand command) {
        pagamentosPedidosService.excluirPagamento(command.getId());
        return ResponseEntity.noContent().build();
    }
}
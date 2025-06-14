package cv.igrp.simple.pedidos.application.commands.handlers;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.simple.pedidos.application.commands.commands.AvaliarPedidoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.CriarAvaliacaoPedidoCommand;
import cv.igrp.simple.pedidos.application.mapper.AvaliacoesPedidosMapper;
import cv.igrp.simple.pedidos.domain.models.AvaliacoesPedidosEntity;
import cv.igrp.simple.pedidos.domain.service.AvaliacoesPedidosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CriarAvaliacaoPedidoCommandHandler implements CommandHandler<CriarAvaliacaoPedidoCommand, ResponseEntity<Integer>> {

    private final AvaliacoesPedidosService avaliacoesPedidosService;
    private final AvaliacoesPedidosMapper avaliacoesPedidosMapper;

    public CriarAvaliacaoPedidoCommandHandler(AvaliacoesPedidosService avaliacoesPedidosService,
                                             AvaliacoesPedidosMapper avaliacoesPedidosMapper) {
        this.avaliacoesPedidosService = avaliacoesPedidosService;
        this.avaliacoesPedidosMapper = avaliacoesPedidosMapper;
    }

    @Override
    public ResponseEntity<Integer> handle(CriarAvaliacaoPedidoCommand command) {
        // Converter DTO para entidade
        AvaliacoesPedidosEntity avaliacao = avaliacoesPedidosMapper.toEntity(command.getCreateAvaliacaoPedidoDTO());
        
        // Salvar a avaliação
        AvaliacoesPedidosEntity avaliacaoSalva = avaliacoesPedidosService.criarAvaliacao(avaliacao);
        
        // Retornar o ID da avaliação criada
        return new ResponseEntity<>(avaliacaoSalva.getId(), HttpStatus.CREATED);
    }
}
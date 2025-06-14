package cv.igrp.simple.pedidos.infrastructure.controller;

import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.framework.stereotype.IgrpController;
import cv.igrp.simple.pedidos.application.commands.commands.CriarAvaliacaoPedidoCommand;
import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.CreateAvaliacaoPedidoDTO;
import cv.igrp.simple.pedidos.application.queries.queries.ObterAvaliacaoPedidoQuery;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@IgrpController
@RestController
@RequestMapping("pedidos/v1/avaliacoes")
@Tag(name = "Avaliações de Pedidos", description = "API para gerenciamento de avaliações de pedidos")
public class AvaliacoesPedidosController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public AvaliacoesPedidosController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @Operation(summary = "Criar uma avaliação para um pedido", description = "Cria uma nova avaliação para um pedido específico")
    @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso")
    @ApiResponse(responseCode = "400", description = "Dados inválidos")
    @ApiResponse(responseCode = "409", description = "Já existe uma avaliação para este pedido")
    @PostMapping
    public ResponseEntity<?> criarAvaliacao(@Valid @RequestBody CreateAvaliacaoPedidoDTO createAvaliacaoPedidoDTO) {
        CriarAvaliacaoPedidoCommand command = new CriarAvaliacaoPedidoCommand(createAvaliacaoPedidoDTO);
        return (ResponseEntity<?>) commandBus.send(command);
    }

    @Operation(summary = "Obter avaliação de um pedido", description = "Retorna a avaliação de um pedido específico")
    @ApiResponse(responseCode = "200", description = "Avaliação encontrada")
    @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    @GetMapping("/{pedidoId}")
    public ResponseEntity<AvaliacaoPedidoResponseDTO> obterAvaliacaoPedido(@PathVariable Integer pedidoId) {
        ObterAvaliacaoPedidoQuery query = new ObterAvaliacaoPedidoQuery(pedidoId);
        return (ResponseEntity<AvaliacaoPedidoResponseDTO>) queryBus.handle(query);
    }
}
package cv.igrp.simple.pedidos.infrastructure.controller;

import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.framework.stereotype.IgrpController;
import cv.igrp.simple.pedidos.application.commands.commands.AtualizarStatusPagamentoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.ExcluirPagamentoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.RegistrarPagamentoPedidoCommand;
import cv.igrp.simple.pedidos.application.dto.CreatePagamentoPedidoDTO;
import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.UpdateStatusPagamentoDTO;
import cv.igrp.simple.pedidos.application.queries.queries.ObterPagamentoQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@IgrpController
@RestController
@RequestMapping(path = "pedidos/v1/pagamentos")
@Tag(name = "PagamentosPedidos", description = "Gerenciamento de pagamentos de pedidos")
public class PagamentosPedidosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentosPedidosController.class);

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PagamentosPedidosController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @GetMapping(value = "/{id}")
    @Operation(
            summary = "GET method to handle operations for obterPagamento",
            description = "GET method to handle operations for obterPagamento",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PagamentoPedidoResponseDTO.class,
                                            type = "object")
                            )
                    )
            }
    )
    public ResponseEntity<PagamentoPedidoResponseDTO> obterPagamento(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentosPedidosController", "obterPagamento");
        final var query = new ObterPagamentoQuery(id);
        ResponseEntity<PagamentoPedidoResponseDTO> response = queryBus.handle(query);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PagamentosPedidosController", "obterPagamento");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @PutMapping(value = "/{id}/status")
    @Operation(
            summary = "PUT method to handle operations for atualizarStatusPagamento",
            description = "PUT method to handle operations for atualizarStatusPagamento",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    )
            }
    )
    public ResponseEntity<Void> atualizarStatusPagamento(
            @Valid @RequestBody UpdateStatusPagamentoDTO updateStatusPagamentoDTO,
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentosPedidosController", "atualizarStatusPagamento");
        final var command = new AtualizarStatusPagamentoCommand(updateStatusPagamentoDTO, id);
        ResponseEntity<Void> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PagamentosPedidosController", "atualizarStatusPagamento");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .build();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "DELETE method to handle operations for excluirPagamento",
            description = "DELETE method to handle operations for excluirPagamento",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    )
            }
    )
    public ResponseEntity<Void> excluirPagamento(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentosPedidosController", "excluirPagamento");
        final var command = new ExcluirPagamentoCommand(id);
        ResponseEntity<Void> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PagamentosPedidosController", "excluirPagamento");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .build();
    }
    
    @PostMapping
    @Operation(
            summary = "POST method to handle operations for registrarPagamento",
            description = "POST method to handle operations for registrarPagamento",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "integer",
                                            format = "int32")
                            )
                    )
            }
    )
    public ResponseEntity<Integer> registrarPagamento(
            @Valid @RequestBody CreatePagamentoPedidoDTO createPagamentoPedidoDTO) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentosPedidosController", "registrarPagamento");
        final var command = new RegistrarPagamentoPedidoCommand(createPagamentoPedidoDTO, createPagamentoPedidoDTO.getPedidoId());
        ResponseEntity<Integer> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PagamentosPedidosController", "registrarPagamento");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }
}
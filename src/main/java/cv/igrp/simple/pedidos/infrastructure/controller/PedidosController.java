package cv.igrp.simple.pedidos.infrastructure.controller;

import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.framework.stereotype.IgrpController;
import cv.igrp.simple.pedidos.application.dto.*;
import cv.igrp.simple.pedidos.application.commands.commands.CriarPedidoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.AtualizarPedidoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.AdicionarHistoricoPedidoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.UploadDocumentoPedidoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.RegistrarPagamentoPedidoCommand;
import cv.igrp.simple.pedidos.application.commands.commands.AvaliarPedidoCommand;
import cv.igrp.simple.pedidos.application.queries.queries.ListarPedidosQuery;
import cv.igrp.simple.pedidos.application.queries.queries.ObterPedidoQuery;
import cv.igrp.simple.pedidos.application.queries.queries.ListarHistoricoPedidoQuery;
import cv.igrp.simple.pedidos.application.queries.queries.ListarDocumentosPedidoQuery;
import cv.igrp.simple.pedidos.application.queries.queries.ListarPagamentosPedidoQuery;
import cv.igrp.simple.pedidos.application.queries.queries.ObterAvaliacaoPedidoQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.apache.commons.compress.harmony.pack200.NewAttributeBands.Integral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@IgrpController
@RestController
@RequestMapping(path = "pedidos/v1")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public class PedidosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PedidosController.class);

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PedidosController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @PostMapping
    @Operation(
            summary = "POST method to handle operations for criarPedido",
            description = "POST method to handle operations for criarPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "string",
                                            format = "integer")
                            )
                    )
            }
    )
    public ResponseEntity<Integer> criarPedido(
            @Valid @RequestBody CreatePedidoDTO createPedidoDTO) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "criarPedido");
        final var command = new CriarPedidoCommand(createPedidoDTO);
        ResponseEntity<Integer> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "criarPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @GetMapping
    @Operation(
            summary = "GET method to handle operations for listarPedidos",
            description = "GET method to handle operations for listarPedidos",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PedidoResponseDTO.class,
                                            type = "object")
                            )
                    )
            }
    )
    public ResponseEntity<Page<PedidoResponseDTO>> listarPedidos(
            @RequestParam(value = "codigoAcompanhamento", required = false) String codigoAcompanhamento,
            @RequestParam(value = "tipoServicoId", required = false) Integer tipoServicoId,
            @RequestParam(value = "cidadaoId", required = false) Integer cidadaoId,
            @RequestParam(value = "userResponsavelId", required = false) Integer userResponsavelId,
            @RequestParam(value = "etapaAtualId", required = false) Integer etapaAtualId,
            @RequestParam(value = "statusId", required = false) Integer statusId,
            @RequestParam(value = "dataInicio", required = false) LocalDateTime dataInicio,
            @RequestParam(value = "dataInicioFim", required = false) LocalDateTime dataInicioFim,
            @RequestParam(value = "dataConclusao", required = false) LocalDateTime dataConclusao,
            @RequestParam(value = "dataConclusaoFim", required = false) LocalDateTime dataConclusaoFim,
            @RequestParam(value = "origem", required = false) String origem,
            @RequestParam(value = "prioridade", required = false) Integer prioridade,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(value = "sort", defaultValue = "dataInicio,desc", required = false) String sort) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "listarPedidos");
        final var query = new ListarPedidosQuery(codigoAcompanhamento, tipoServicoId, cidadaoId, userResponsavelId,
                etapaAtualId, statusId, dataInicio, dataInicioFim, dataConclusao, dataConclusaoFim, origem, prioridade,
                page, size, sort);
        ResponseEntity<Page<PedidoResponseDTO>> response = queryBus.handle(query);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "listarPedidos");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @GetMapping(value = "/{id}")
    @Operation(
            summary = "GET method to handle operations for obterPedido",
            description = "GET method to handle operations for obterPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PedidoResponseDTO.class,
                                            type = "object")
                            )
                    )
            }
    )
    public ResponseEntity<PedidoResponseDTO> obterPedido(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "obterPedido");
        final var query = new ObterPedidoQuery(id);
        ResponseEntity<PedidoResponseDTO> response = queryBus.handle(query);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "obterPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @PutMapping(value = "/{id}")
    @Operation(
            summary = "PUT method to handle operations for atualizarPedido",
            description = "PUT method to handle operations for atualizarPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "No Content"
                    )
            }
    )
    public ResponseEntity<Void> atualizarPedido(
            @Valid @RequestBody UpdatePedidoDTO updatePedidoDTO,
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "atualizarPedido");
        final var command = new AtualizarPedidoCommand(updatePedidoDTO, id);
        ResponseEntity<Void> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "atualizarPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .build();
    }

    @GetMapping(value = "/{id}/historico")
    @Operation(
            summary = "GET method to handle operations for listarHistoricoPedido",
            description = "GET method to handle operations for listarHistoricoPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = HistoricoPedidoResponseDTO.class,
                                            type = "array")
                            )
                    )
            }
    )
    public ResponseEntity<List<HistoricoPedidoResponseDTO>> listarHistoricoPedido(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "listarHistoricoPedido");
        final var query = new ListarHistoricoPedidoQuery(id);
        ResponseEntity<List<HistoricoPedidoResponseDTO>> response = queryBus.handle(query);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "listarHistoricoPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @PostMapping(value = "/{id}/historico")
    @Operation(
            summary = "POST method to handle operations for adicionarHistoricoPedido",
            description = "POST method to handle operations for adicionarHistoricoPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created"
                    )
            }
    )
    public ResponseEntity<Void> adicionarHistoricoPedido(
            @Valid @RequestBody CreateHistoricoPedidoDTO createHistoricoPedidoDTO,
            @PathVariable(value = "id") UUID id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "adicionarHistoricoPedido");
        final var command = new AdicionarHistoricoPedidoCommand(createHistoricoPedidoDTO, id);
        ResponseEntity<Void> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "adicionarHistoricoPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .build();
    }

    @GetMapping(value = "/{id}/documentos")
    @Operation(
            summary = "GET method to handle operations for listarDocumentosPedido",
            description = "GET method to handle operations for listarDocumentosPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = DocumentoPedidoResponseDTO.class,
                                            type = "array")
                            )
                    )
            }
    )
    public ResponseEntity<List<DocumentoPedidoResponseDTO>> listarDocumentosPedido(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "listarDocumentosPedido");
        final var query = new ListarDocumentosPedidoQuery(id);
        ResponseEntity<List<DocumentoPedidoResponseDTO>> response = queryBus.handle(query);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "listarDocumentosPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @PostMapping(value = "/{id}/documentos")
    @Operation(
            summary = "POST method to handle operations for uploadDocumentoPedido",
            description = "POST method to handle operations for uploadDocumentoPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "string",
                                            format = "integer")
                            )
                    )
            }
    )
    public ResponseEntity<UUID> uploadDocumentoPedido(
            @Valid @RequestBody UploadDocumentoPedidoDTO uploadDocumentoPedidoDTO,
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "uploadDocumentoPedido");
        final var command = new UploadDocumentoPedidoCommand(uploadDocumentoPedidoDTO, id);
        ResponseEntity<UUID> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "uploadDocumentoPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @GetMapping(value = "/{id}/pagamentos")
    @Operation(
            summary = "GET method to handle operations for listarPagamentosPedido",
            description = "GET method to handle operations for listarPagamentosPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PagamentoPedidoResponseDTO.class,
                                            type = "array")
                            )
                    )
            }
    )
    public ResponseEntity<List<PagamentoPedidoResponseDTO>> listarPagamentosPedido(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "listarPagamentosPedido");
        final var query = new ListarPagamentosPedidoQuery(id);
        ResponseEntity<List<PagamentoPedidoResponseDTO>> response = queryBus.handle(query);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "listarPagamentosPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @PostMapping(value = "/{id}/pagamentos")
    @Operation(
            summary = "POST method to handle operations for registrarPagamentoPedido",
            description = "POST method to handle operations for registrarPagamentoPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            type = "string",
                                            format = "integer")
                            )
                    )
            }
    )
    public ResponseEntity<UUID> registrarPagamentoPedido(
            @Valid @RequestBody CreatePagamentoPedidoDTO createPagamentoPedidoDTO,
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "registrarPagamentoPedido");
        final var command = new RegistrarPagamentoPedidoCommand(createPagamentoPedidoDTO, id);
        ResponseEntity<UUID> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "registrarPagamentoPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @GetMapping(value = "/{id}/avaliacao")
    @Operation(
            summary = "GET method to handle operations for obterAvaliacaoPedido",
            description = "GET method to handle operations for obterAvaliacaoPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = AvaliacaoPedidoResponseDTO.class,
                                            type = "object")
                            )
                    )
            }
    )
    public ResponseEntity<AvaliacaoPedidoResponseDTO> obterAvaliacaoPedido(
            @PathVariable(value = "id") Integer id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "obterAvaliacaoPedido");
        final var query = new ObterAvaliacaoPedidoQuery(id);
        ResponseEntity<AvaliacaoPedidoResponseDTO> response = queryBus.handle(query);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "obterAvaliacaoPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .body(response.getBody());
    }

    @PostMapping(value = "/{id}/avaliacao")
    @Operation(
            summary = "POST method to handle operations for avaliarPedido",
            description = "POST method to handle operations for avaliarPedido",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created"
                    )
            }
    )
    public ResponseEntity<Void> avaliarPedido(
            @Valid @RequestBody CreateAvaliacaoPedidoDTO createAvaliacaoPedidoDTO,
            @PathVariable(value = "id") UUID id) {
        LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidosController", "avaliarPedido");
        final var command = new AvaliarPedidoCommand(createAvaliacaoPedidoDTO, id);
        ResponseEntity<Void> response = commandBus.send(command);
        LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidosController", "avaliarPedido");
        return ResponseEntity.status(response.getStatusCode())
                .headers(response.getHeaders())
                .build();
    }
}
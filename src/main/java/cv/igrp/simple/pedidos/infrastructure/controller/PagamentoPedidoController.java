package cv.igrp.simple.pedidos.infrastructure.controller;

import cv.igrp.framework.stereotype.IgrpController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.simple.pedidos.application.commands.*;
import cv.igrp.simple.pedidos.application.queries.*;


import java.util.List;
import cv.igrp.simple.pedidos.application.dto.PagamentoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.CreatePagamentoPedidoDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/pedidos")
@Tag(name = "PagamentoPedido", description = "pagamentos")
public class PagamentoPedidoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoPedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public PagamentoPedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
    value = "{pedidoId}/pagamentos"
  )
  @Operation(
    summary = "GET method to handle operations for listPagamentosPedido",
    description = "GET method to handle operations for listPagamentosPedido",
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
  
  public ResponseEntity<List<PagamentoPedidoResponseDTO>> listPagamentosPedido(
    @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentoPedidoController", "listPagamentosPedido");
      final var query = new ListPagamentosPedidoQuery(pedidoId);

      ResponseEntity<List<PagamentoPedidoResponseDTO>> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
    value = "{pedidoId}/pagamentos"
  )
  @Operation(
    summary = "POST method to handle operations for registarPagamentoPedido",
    description = "POST method to handle operations for registarPagamentoPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = String.class,
                  type = "String")
          )
      )
    }
  )
  
  public ResponseEntity<Map<String, ?>> registarPagamentoPedido(@Valid @RequestBody CreatePagamentoPedidoDTO registarPagamentoPedidoRequest
    , @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentoPedidoController", "registarPagamentoPedido");
      final var command = new RegistarPagamentoPedidoCommand(registarPagamentoPedidoRequest, pedidoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PagamentoPedidoController", "registarPagamentoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{pedidoId}/pagamentos/{pagamentoId}"
  )
  @Operation(
    summary = "GET method to handle operations for getPagamentoPedidoById",
    description = "GET method to handle operations for getPagamentoPedidoById",
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
  
  public ResponseEntity<PagamentoPedidoResponseDTO> getPagamentoPedidoById(
    @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "pagamentoId") String pagamentoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentoPedidoController", "getPagamentoPedidoById");
      final var query = new GetPagamentoPedidoByIdQuery(pedidoId, pagamentoId);

      ResponseEntity<PagamentoPedidoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @DeleteMapping(
    value = "{pedidoId}/pagamentos/{pagamentoId}"
  )
  @Operation(
    summary = "DELETE method to handle operations for inativarPagamentoPedido",
    description = "DELETE method to handle operations for inativarPagamentoPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = String.class,
                  type = "String")
          )
      )
    }
  )
  
  public ResponseEntity<Map<String, ?>> inativarPagamentoPedido(
    @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "pagamentoId") String pagamentoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentoPedidoController", "inativarPagamentoPedido");
      final var command = new InativarPagamentoPedidoCommand(pedidoId, pagamentoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PagamentoPedidoController", "inativarPagamentoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{pedidoId}/pagamentos/{pagamentoId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updatePagamentoPedido",
    description = "PUT method to handle operations for updatePagamentoPedido",
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
  
  public ResponseEntity<PagamentoPedidoResponseDTO> updatePagamentoPedido(
    @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "pagamentoId") String pagamentoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PagamentoPedidoController", "updatePagamentoPedido");
      final var command = new UpdatePagamentoPedidoCommand(pedidoId, pagamentoId);

       ResponseEntity<PagamentoPedidoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PagamentoPedidoController", "updatePagamentoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
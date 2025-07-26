package cv.igrp.simple.configuracoes.infrastructure.controller;

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
import cv.igrp.simple.configuracoes.application.commands.*;
import cv.igrp.simple.configuracoes.application.queries.*;


import java.util.List;
import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;
import cv.igrp.simple.configuracoes.application.dto.CreateStatusPedidoDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "configuracoes/v1/statusPedido")
@Tag(name = "StatusPedido", description = "status management")
public class StatusPedidoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StatusPedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public StatusPedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for listStatusPedido",
    description = "GET method to handle operations for listStatusPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = StatusPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<List<StatusPedidoResponseDTO>> listStatusPedido(
    @RequestParam(value = "codigo", required = false) String codigo,
    @RequestParam(value = "tamanho", defaultValue = "20") String tamanho,
    @RequestParam(value = "pagina", defaultValue = "0") String pagina)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "StatusPedidoController", "listStatusPedido");
      final var query = new ListStatusPedidoQuery(codigo, tamanho, pagina);

      ResponseEntity<List<StatusPedidoResponseDTO>> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{statusPedidoId}"
  )
  @Operation(
    summary = "GET method to handle operations for getStatusPedidoById",
    description = "GET method to handle operations for getStatusPedidoById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = StatusPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<StatusPedidoResponseDTO> getStatusPedidoById(
    @PathVariable(value = "statusPedidoId") String statusPedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "StatusPedidoController", "getStatusPedidoById");
      final var query = new GetStatusPedidoByIdQuery(statusPedidoId);

      ResponseEntity<StatusPedidoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{statusPedidoId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateStatusPedido",
    description = "PUT method to handle operations for updateStatusPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = StatusPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<StatusPedidoResponseDTO> updateStatusPedido(@Valid @RequestBody CreateStatusPedidoDTO updateStatusPedidoRequest
    , @PathVariable(value = "statusPedidoId") String statusPedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "StatusPedidoController", "updateStatusPedido");
      final var command = new UpdateStatusPedidoCommand(updateStatusPedidoRequest, statusPedidoId);

       ResponseEntity<StatusPedidoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "StatusPedidoController", "updateStatusPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createStatusPedido",
    description = "POST method to handle operations for createStatusPedido",
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
  
  public ResponseEntity<Map<String, ?>> createStatusPedido(@Valid @RequestBody CreateStatusPedidoDTO createStatusPedidoRequest
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "StatusPedidoController", "createStatusPedido");
      final var command = new CreateStatusPedidoCommand(createStatusPedidoRequest);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "StatusPedidoController", "createStatusPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @DeleteMapping(
    value = "{statusPedidoId}"
  )
  @Operation(
    summary = "DELETE method to handle operations for inativarStatusPedido",
    description = "DELETE method to handle operations for inativarStatusPedido",
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
  
  public ResponseEntity<Map<String, ?>> inativarStatusPedido(
    @PathVariable(value = "statusPedidoId") String statusPedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "StatusPedidoController", "inativarStatusPedido");
      final var command = new InativarStatusPedidoCommand(statusPedidoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "StatusPedidoController", "inativarStatusPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
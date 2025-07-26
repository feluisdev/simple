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
import cv.igrp.simple.pedidos.application.dto.HistoricoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.CreateHistoricoPedidoDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/pedidos")
@Tag(name = "HistoricoPedido", description = "historicos")
public class HistoricoPedidoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HistoricoPedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public HistoricoPedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
    value = "{pedidoId}/historico"
  )
  @Operation(
    summary = "GET method to handle operations for listHistoricoPedido",
    description = "GET method to handle operations for listHistoricoPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = HistoricoPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<List<HistoricoPedidoResponseDTO>> listHistoricoPedido(
    @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "HistoricoPedidoController", "listHistoricoPedido");
      final var query = new ListHistoricoPedidoQuery(pedidoId);

      ResponseEntity<List<HistoricoPedidoResponseDTO>> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
    value = "{pedidoId}/historico"
  )
  @Operation(
    summary = "POST method to handle operations for addHistoricoPedido",
    description = "POST method to handle operations for addHistoricoPedido",
    responses = {
      @ApiResponse(
          responseCode = "201",
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
  
  public ResponseEntity<Map<String, ?>> addHistoricoPedido(@Valid @RequestBody CreateHistoricoPedidoDTO addHistoricoPedidoRequest
    , @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "HistoricoPedidoController", "addHistoricoPedido");
      final var command = new AddHistoricoPedidoCommand(addHistoricoPedidoRequest, pedidoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "HistoricoPedidoController", "addHistoricoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{pedidoId}/historico/{historicoId}"
  )
  @Operation(
    summary = "GET method to handle operations for getHistoricoPedidoById",
    description = "GET method to handle operations for getHistoricoPedidoById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = HistoricoPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<HistoricoPedidoResponseDTO> getHistoricoPedidoById(
    @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "historicoId") String historicoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "HistoricoPedidoController", "getHistoricoPedidoById");
      final var query = new GetHistoricoPedidoByIdQuery(pedidoId, historicoId);

      ResponseEntity<HistoricoPedidoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
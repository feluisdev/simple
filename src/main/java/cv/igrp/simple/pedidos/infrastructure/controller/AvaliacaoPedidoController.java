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


import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.CreateAvaliacaoPedidoDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/pedidos")
@Tag(name = "AvaliacaoPedido", description = "avaliacao")
public class AvaliacaoPedidoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(AvaliacaoPedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public AvaliacaoPedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
    value = "{pedidoId}/avaliacao"
  )
  @Operation(
    summary = "GET method to handle operations for getAvaliacaoPedido",
    description = "GET method to handle operations for getAvaliacaoPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = AvaliacaoPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<AvaliacaoPedidoResponseDTO> getAvaliacaoPedido(
    @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "AvaliacaoPedidoController", "getAvaliacaoPedido");
      final var query = new GetAvaliacaoPedidoQuery(pedidoId);

      ResponseEntity<AvaliacaoPedidoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
    value = "{pedidoId}/avaliacao"
  )
  @Operation(
    summary = "POST method to handle operations for addAvaliacaoPedido",
    description = "POST method to handle operations for addAvaliacaoPedido",
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
  
  public ResponseEntity<Map<String, ?>> addAvaliacaoPedido(@Valid @RequestBody CreateAvaliacaoPedidoDTO addAvaliacaoPedidoRequest
    , @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "AvaliacaoPedidoController", "addAvaliacaoPedido");
      final var command = new AddAvaliacaoPedidoCommand(addAvaliacaoPedidoRequest, pedidoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "AvaliacaoPedidoController", "addAvaliacaoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{pedidoId}/avaliacao/{avaliacaoId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateAvaliacao",
    description = "PUT method to handle operations for updateAvaliacao",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = AvaliacaoPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<AvaliacaoPedidoResponseDTO> updateAvaliacao(@Valid @RequestBody CreateAvaliacaoPedidoDTO updateAvaliacaoRequest
    , @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "avaliacaoId") String avaliacaoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "AvaliacaoPedidoController", "updateAvaliacao");
      final var command = new UpdateAvaliacaoCommand(updateAvaliacaoRequest, pedidoId, avaliacaoId);

       ResponseEntity<AvaliacaoPedidoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "AvaliacaoPedidoController", "updateAvaliacao");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
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


import cv.igrp.simple.pedidos.application.dto.PedidoRequestDTO;
import java.util.Map;
import cv.igrp.simple.pedidos.application.dto.WrapperListaPedidoDTO;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;
import java.util.List;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/pedidos")
@Tag(name = "Pedido", description = "Gerenciamento de Pedidos")
public class PedidoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public PedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createPedido",
    description = "POST method to handle operations for createPedido",
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
  
  public ResponseEntity<Map<String, ?>> createPedido(@Valid @RequestBody PedidoRequestDTO createPedidoRequest
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "createPedido");
      final var command = new CreatePedidoCommand(createPedidoRequest);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidoController", "createPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for listPedido",
    description = "GET method to handle operations for listPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListaPedidoDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListaPedidoDTO> listPedido(
    @RequestParam(value = "tipoServicoId", required = false) String tipoServicoId,
    @RequestParam(value = "codigoAcompanhamento", required = false) String codigoAcompanhamento,
    @RequestParam(value = "utenteId", required = false) Integer utenteId,
    @RequestParam(value = "nomeUtente", required = false) String nomeUtente,
    @RequestParam(value = "numeroUtente", required = false) String numeroUtente,
    @RequestParam(value = "dataDe", required = false) String dataDe,
    @RequestParam(value = "dataAte", required = false) String dataAte,
    @RequestParam(value = "pagina", defaultValue = "0") String pagina,
    @RequestParam(value = "tamanho", defaultValue = "20") String tamanho)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "listPedido");
      final var query = new ListPedidoQuery(tipoServicoId, codigoAcompanhamento, utenteId, nomeUtente, numeroUtente, dataDe, dataAte, pagina, tamanho);

      ResponseEntity<WrapperListaPedidoDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{pedidoId}"
  )
  @Operation(
    summary = "GET method to handle operations for getPedidoById",
    description = "GET method to handle operations for getPedidoById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = PedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<PedidoResponseDTO> getPedidoById(
    @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "getPedidoById");
      final var query = new GetPedidoByIdQuery(pedidoId);

      ResponseEntity<PedidoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "codigo/{codigoAcompanhamento}"
  )
  @Operation(
    summary = "GET method to handle operations for getPedidoByCodigoAcompanhamento",
    description = "GET method to handle operations for getPedidoByCodigoAcompanhamento",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = PedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<PedidoResponseDTO> getPedidoByCodigoAcompanhamento(
    @PathVariable(value = "codigoAcompanhamento") String codigoAcompanhamento)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "getPedidoByCodigoAcompanhamento");
      final var query = new GetPedidoByCodigoAcompanhamentoQuery(codigoAcompanhamento);

      ResponseEntity<PedidoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{pedidoId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updatePedido",
    description = "PUT method to handle operations for updatePedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = PedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<PedidoResponseDTO> updatePedido(@Valid @RequestBody PedidoRequestDTO updatePedidoRequest
    , @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "updatePedido");
      final var command = new UpdatePedidoCommand(updatePedidoRequest, pedidoId);

       ResponseEntity<PedidoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidoController", "updatePedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @DeleteMapping(
    value = "{pedidoId}"
  )
  @Operation(
    summary = "DELETE method to handle operations for inativarPedido",
    description = "DELETE method to handle operations for inativarPedido",
    responses = {
      @ApiResponse(
          responseCode = "204",
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
  
  public ResponseEntity<Map<String, ?>> inativarPedido(
    @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "inativarPedido");
      final var command = new InativarPedidoCommand(pedidoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidoController", "inativarPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "utente/{utenteId}"
  )
  @Operation(
    summary = "GET method to handle operations for getPedidoByUtente",
    description = "GET method to handle operations for getPedidoByUtente",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = PedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<List<PedidoResponseDTO>> getPedidoByUtente(
    @PathVariable(value = "utenteId") String utenteId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "getPedidoByUtente");
      final var query = new GetPedidoByUtenteQuery(utenteId);

      ResponseEntity<List<PedidoResponseDTO>> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "/{pedidoId}/status/{statusId}"
  )
  @Operation(
    summary = "PATCH method to handle operations for updateStatus",
    description = "PATCH method to handle operations for updateStatus",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = PedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<PedidoResponseDTO> updateStatus(
    @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "statusId") String statusId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "updateStatus");
      final var command = new UpdateStatusCommand(pedidoId, statusId);

       ResponseEntity<PedidoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidoController", "updateStatus");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
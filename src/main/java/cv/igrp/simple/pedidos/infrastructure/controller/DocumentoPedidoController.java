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
import cv.igrp.simple.pedidos.application.dto.DocumentoPedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.UploadDocumentoPedidoDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/pedidos")
@Tag(name = "DocumentoPedido", description = "documentos")
public class DocumentoPedidoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoPedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public DocumentoPedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
    value = "{pedidoId}/documentos"
  )
  @Operation(
    summary = "GET method to handle operations for listPedidoDocumentos",
    description = "GET method to handle operations for listPedidoDocumentos",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = DocumentoPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<List<DocumentoPedidoResponseDTO>> listPedidoDocumentos(
    @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "DocumentoPedidoController", "listPedidoDocumentos");
      final var query = new ListPedidoDocumentosQuery(pedidoId);

      ResponseEntity<List<DocumentoPedidoResponseDTO>> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
    value = "{pedidoId}/documentos"
  )
  @Operation(
    summary = "POST method to handle operations for addDocumentoPedido",
    description = "POST method to handle operations for addDocumentoPedido",
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
  
  public ResponseEntity<Map<String, ?>> addDocumentoPedido(@Valid @RequestBody UploadDocumentoPedidoDTO addDocumentoPedidoRequest
    , @PathVariable(value = "pedidoId") String pedidoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "DocumentoPedidoController", "addDocumentoPedido");
      final var command = new AddDocumentoPedidoCommand(addDocumentoPedidoRequest, pedidoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "DocumentoPedidoController", "addDocumentoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{pedidoId}/documentos{documentoId}"
  )
  @Operation(
    summary = "GET method to handle operations for getDocumentoPedidoById",
    description = "GET method to handle operations for getDocumentoPedidoById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = DocumentoPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<DocumentoPedidoResponseDTO> getDocumentoPedidoById(
    @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "documentoId") String documentoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "DocumentoPedidoController", "getDocumentoPedidoById");
      final var query = new GetDocumentoPedidoByIdQuery(pedidoId, documentoId);

      ResponseEntity<DocumentoPedidoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @DeleteMapping(
    value = "{pedidoId}/documentos{documentoId}"
  )
  @Operation(
    summary = "DELETE method to handle operations for inativarDocumentoPedido",
    description = "DELETE method to handle operations for inativarDocumentoPedido",
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
  
  public ResponseEntity<Map<String, ?>> inativarDocumentoPedido(
    @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "documentoId") String documentoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "DocumentoPedidoController", "inativarDocumentoPedido");
      final var command = new InativarDocumentoPedidoCommand(pedidoId, documentoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "DocumentoPedidoController", "inativarDocumentoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{pedidoId}/documentos{documentoId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateDocumentoPedido",
    description = "PUT method to handle operations for updateDocumentoPedido",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = DocumentoPedidoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<DocumentoPedidoResponseDTO> updateDocumentoPedido(@Valid @RequestBody UploadDocumentoPedidoDTO updateDocumentoPedidoRequest
    , @PathVariable(value = "pedidoId") String pedidoId,@PathVariable(value = "documentoId") String documentoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "DocumentoPedidoController", "updateDocumentoPedido");
      final var command = new UpdateDocumentoPedidoCommand(updateDocumentoPedidoRequest, pedidoId, documentoId);

       ResponseEntity<DocumentoPedidoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "DocumentoPedidoController", "updateDocumentoPedido");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
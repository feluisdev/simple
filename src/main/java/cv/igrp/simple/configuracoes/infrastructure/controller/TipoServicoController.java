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


import cv.igrp.simple.configuracoes.application.dto.CriarTiposServicosDTO;
import java.util.Map;
import cv.igrp.simple.configuracoes.application.dto.WrapperListaTipoServicoDTO;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;

@IgrpController
@RestController
@RequestMapping(path = "configuracoes/v1/tiposervico")
@Tag(name = "TipoServico", description = "gestao tipo servico")
public class TipoServicoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TipoServicoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public TipoServicoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createTipoServico",
    description = "POST method to handle operations for createTipoServico",
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
  
  public ResponseEntity<Map<String, ?>> createTipoServico(@Valid @RequestBody CriarTiposServicosDTO createTipoServicoRequest
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "TipoServicoController", "createTipoServico");
      final var command = new CreateTipoServicoCommand(createTipoServicoRequest);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "TipoServicoController", "createTipoServico");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for listaTipoServico",
    description = "GET method to handle operations for listaTipoServico",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListaTipoServicoDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListaTipoServicoDTO> listaTipoServico(
    @RequestParam(value = "nome", required = false) String nome,
    @RequestParam(value = "codigo", required = false) String codigo,
    @RequestParam(value = "pagina", required = false, defaultValue = "0") String pagina,
    @RequestParam(value = "tamanho", required = false, defaultValue = "20") String tamanho)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "TipoServicoController", "listaTipoServico");
      final var query = new ListaTipoServicoQuery(nome, codigo, pagina, tamanho);

      ResponseEntity<WrapperListaTipoServicoDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{tipoServicoId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateTipoServico",
    description = "PUT method to handle operations for updateTipoServico",
    responses = {
      @ApiResponse(
          responseCode = "201",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = TiposServicosResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<TiposServicosResponseDTO> updateTipoServico(@Valid @RequestBody CriarTiposServicosDTO updateTipoServicoRequest
    , @PathVariable(value = "tipoServicoId") String tipoServicoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "TipoServicoController", "updateTipoServico");
      final var command = new UpdateTipoServicoCommand(updateTipoServicoRequest, tipoServicoId);

       ResponseEntity<TiposServicosResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "TipoServicoController", "updateTipoServico");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{tipoServicoId}"
  )
  @Operation(
    summary = "GET method to handle operations for getTipoServico",
    description = "GET method to handle operations for getTipoServico",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = TiposServicosResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<TiposServicosResponseDTO> getTipoServico(
    @PathVariable(value = "tipoServicoId") String tipoServicoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "TipoServicoController", "getTipoServico");
      final var query = new GetTipoServicoQuery(tipoServicoId);

      ResponseEntity<TiposServicosResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @DeleteMapping(
    value = "{tipoServicoId}"
  )
  @Operation(
    summary = "DELETE method to handle operations for InativarTipoServico",
    description = "DELETE method to handle operations for InativarTipoServico",
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
  
  public ResponseEntity<Map<String, ?>> inativarTipoServico(
    @PathVariable(value = "tipoServicoId") String tipoServicoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "TipoServicoController", "InativarTipoServico");
      final var command = new InativarTipoServicoCommand(tipoServicoId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "TipoServicoController", "InativarTipoServico");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
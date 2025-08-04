/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.licenciamento.interfaces.rest;

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
import cv.igrp.simple.licenciamento.application.commands.*;
import cv.igrp.simple.licenciamento.application.queries.*;


import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeRequestDTO;
import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.WrapperListaTipoAtividadeDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/tipos-atividade")
@Tag(name = "TipoAtividade", description = "gestao tipo Atividade")
public class TipoAtividadeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(TipoAtividadeController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public TipoAtividadeController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createTipoAtividade",
    description = "POST method to handle operations for createTipoAtividade",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = TipoAtividadeResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<TipoAtividadeResponseDTO> createTipoAtividade(@Valid @RequestBody TipoAtividadeRequestDTO createTipoAtividadeRequest
    )
  {

      LOGGER.debug("Operation started");

      final var command = new CreateTipoAtividadeCommand(createTipoAtividadeRequest);

       ResponseEntity<TipoAtividadeResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{idTipoAtividade}"
  )
  @Operation(
    summary = "GET method to handle operations for getTipoAtividadeById",
    description = "GET method to handle operations for getTipoAtividadeById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = TipoAtividadeResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<TipoAtividadeResponseDTO> getTipoAtividadeById(
    @PathVariable(value = "idTipoAtividade") String idTipoAtividade)
  {

      LOGGER.debug("Operation started");

      final var query = new GetTipoAtividadeByIdQuery(idTipoAtividade);

      ResponseEntity<TipoAtividadeResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for getTiposAtividade",
    description = "GET method to handle operations for getTiposAtividade",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListaTipoAtividadeDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListaTipoAtividadeDTO> getTiposAtividade(
    @RequestParam(value = "codigo", required = false) String codigo,
    @RequestParam(value = "nome", required = false) String nome,
    @RequestParam(value = "estado", required = false) String estado,
    @RequestParam(value = "pagina", defaultValue = "0") String pagina,
    @RequestParam(value = "tamanho", defaultValue = "20") String tamanho)
  {

      LOGGER.debug("Operation started");

      final var query = new GetTiposAtividadeQuery(codigo, nome, estado, pagina, tamanho);

      ResponseEntity<WrapperListaTipoAtividadeDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{idTipoAtividade}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateTipoAtividade",
    description = "PUT method to handle operations for updateTipoAtividade",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = TipoAtividadeResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<TipoAtividadeResponseDTO> updateTipoAtividade(@Valid @RequestBody TipoAtividadeRequestDTO updateTipoAtividadeRequest
    , @PathVariable(value = "idTipoAtividade") String idTipoAtividade)
  {

      LOGGER.debug("Operation started");

      final var command = new UpdateTipoAtividadeCommand(updateTipoAtividadeRequest, idTipoAtividade);

       ResponseEntity<TipoAtividadeResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{idTipoAtividade}/ativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for ativarTipoAtividade",
    description = "PATCH method to handle operations for ativarTipoAtividade",
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
  
  public ResponseEntity<Map<String, ?>> ativarTipoAtividade(
    @PathVariable(value = "idTipoAtividade") String idTipoAtividade)
  {

      LOGGER.debug("Operation started");

      final var command = new AtivarTipoAtividadeCommand(idTipoAtividade);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{idTipoAtividade}/desativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for desativarTipoAtividade",
    description = "PATCH method to handle operations for desativarTipoAtividade",
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
  
  public ResponseEntity<Map<String, ?>> desativarTipoAtividade(
    @PathVariable(value = "idTipoAtividade") String idTipoAtividade)
  {

      LOGGER.debug("Operation started");

      final var command = new DesativarTipoAtividadeCommand(idTipoAtividade);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
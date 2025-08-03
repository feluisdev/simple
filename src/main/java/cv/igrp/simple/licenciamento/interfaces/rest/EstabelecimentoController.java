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


import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.WrapperListaEstabelecimentoDTO;
import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoRequestDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/estabelecimentos")
@Tag(name = "Estabelecimento", description = "gestao estabelecimento")
public class EstabelecimentoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(EstabelecimentoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public EstabelecimentoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
    value = "{idEstabelecimento}"
  )
  @Operation(
    summary = "GET method to handle operations for getEstabelecimentoById",
    description = "GET method to handle operations for getEstabelecimentoById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = EstabelecimentoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<EstabelecimentoResponseDTO> getEstabelecimentoById(
    @PathVariable(value = "idEstabelecimento") String idEstabelecimento)
  {

      LOGGER.debug("Operation started");

      final var query = new GetEstabelecimentoByIdQuery(idEstabelecimento);

      ResponseEntity<EstabelecimentoResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for getEstabelecimentos",
    description = "GET method to handle operations for getEstabelecimentos",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListaEstabelecimentoDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListaEstabelecimentoDTO> getEstabelecimentos(
    @RequestParam(value = "nome", required = false) String nome,
    @RequestParam(value = "gerente", required = false) String gerente,
    @RequestParam(value = "estado", required = false) String estado,
    @RequestParam(value = "pagina", defaultValue = "0") String pagina,
    @RequestParam(value = "tamanho", defaultValue = "20") String tamanho)
  {

      LOGGER.debug("Operation started");

      final var query = new GetEstabelecimentosQuery(nome, gerente, estado, pagina, tamanho);

      ResponseEntity<WrapperListaEstabelecimentoDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createEstabelecimento",
    description = "POST method to handle operations for createEstabelecimento",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = EstabelecimentoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<EstabelecimentoResponseDTO> createEstabelecimento(@Valid @RequestBody EstabelecimentoRequestDTO createEstabelecimentoRequest
    )
  {

      LOGGER.debug("Operation started");

      final var command = new CreateEstabelecimentoCommand(createEstabelecimentoRequest);

       ResponseEntity<EstabelecimentoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{idEstabelecimento}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateEstabelecimento",
    description = "PUT method to handle operations for updateEstabelecimento",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = EstabelecimentoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<EstabelecimentoResponseDTO> updateEstabelecimento(@Valid @RequestBody EstabelecimentoRequestDTO updateEstabelecimentoRequest
    , @PathVariable(value = "idEstabelecimento") String idEstabelecimento)
  {

      LOGGER.debug("Operation started");

      final var command = new UpdateEstabelecimentoCommand(updateEstabelecimentoRequest, idEstabelecimento);

       ResponseEntity<EstabelecimentoResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{idEstabelecimento}/ativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for ativarEstabelecimento",
    description = "PATCH method to handle operations for ativarEstabelecimento",
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
  
  public ResponseEntity<Map<String, ?>> ativarEstabelecimento(
    @PathVariable(value = "idEstabelecimento") String idEstabelecimento)
  {

      LOGGER.debug("Operation started");

      final var command = new AtivarEstabelecimentoCommand(idEstabelecimento);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{idEstabelecimento}/desativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for desativarEstabelecimento",
    description = "PATCH method to handle operations for desativarEstabelecimento",
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
  
  public ResponseEntity<Map<String, ?>> desativarEstabelecimento(
    @PathVariable(value = "idEstabelecimento") String idEstabelecimento)
  {

      LOGGER.debug("Operation started");

      final var command = new DesativarEstabelecimentoCommand(idEstabelecimento);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
package cv.igrp.simple.utente.infrastructure.controller;

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
import cv.igrp.simple.utente.application.commands.commands.*;
import cv.igrp.simple.utente.application.queries.queries.*;


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.dto.CriarUtenteDTO;
import cv.igrp.simple.utente.application.dto.UpdateUtenteDTO;

@IgrpController
@RestController
@RequestMapping(path = "utentes/v1")
@Tag(name = "Utente", description = "endpoint para gestao de utente")
public class UtenteController {

   private static final Logger LOGGER = LoggerFactory.getLogger(UtenteController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public UtenteController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for listaDeUtentes",
    description = "GET method to handle operations for listaDeUtentes",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = UtenteResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<Page<UtenteResponseDTO>> listaDeUtentes(
    @RequestParam(value = "tipo", required = false) String tipo,
    @RequestParam(value = "numeroUtente", required = false) String numeroUtente,
    @RequestParam(value = "nome", required = false) String nome,
    @RequestParam(value = "nif", required = false) String nif,
    @RequestParam(value = "documento", required = false) String documento,
    @RequestParam(value = "estado", required = false) String estado,@ParameterObject Pageable pageable)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "UtenteController", "listaDeUtentes");
      final var query = new ListaDeUtentesQuery(tipo, numeroUtente, nome, nif, documento, estado,pageable);
      ResponseEntity<Page<UtenteResponseDTO>> response = queryBus.handle(query);
      LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "UtenteController", "listaDeUtentes");
      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "/{id}"
  )
  @Operation(
    summary = "GET method to handle operations for obterUtente",
    description = "GET method to handle operations for obterUtente",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = UtenteResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<UtenteResponseDTO> obterUtente(
    @PathVariable(value = "id") Integer id)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "UtenteController", "obterUtente");
      final var query = new ObterUtenteQuery(id);
      ResponseEntity<UtenteResponseDTO> response = queryBus.handle(query);
      LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "UtenteController", "obterUtente");
      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for criarUtente",
    description = "POST method to handle operations for criarUtente",
    responses = {
      @ApiResponse(
          responseCode = "201",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = UtenteResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<UtenteResponseDTO> criarUtente(@Valid @RequestBody CriarUtenteDTO utenteDTO
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "UtenteController", "criarUtente");
      final var command = new CriarUtenteCommand(utenteDTO);
       ResponseEntity<UtenteResponseDTO> response = commandBus.send(command);
       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "UtenteController", "criarUtente");
        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "/{id}"
  )
  @Operation(
    summary = "PUT method to handle operations for atualizarUtente",
    description = "PUT method to handle operations for atualizarUtente",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = UtenteResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<UtenteResponseDTO> atualizarUtente(@Valid @RequestBody UpdateUtenteDTO atualizarUtenteRequest
    , @PathVariable(value = "id") Integer id)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "UtenteController", "atualizarUtente");
      final var command = new AtualizarUtenteCommand(atualizarUtenteRequest, id);
       ResponseEntity<UtenteResponseDTO> response = commandBus.send(command);
       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "UtenteController", "atualizarUtente");
        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @DeleteMapping(
    value = "/{id}"
  )
  @Operation(
    summary = "DELETE method to handle operations for inativarUtente",
    description = "DELETE method to handle operations for inativarUtente",
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
  
  public ResponseEntity<String> inativarUtente(
    @PathVariable(value = "id") Integer id)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "UtenteController", "inativarUtente");
      final var command = new InativarUtenteCommand(id);
       ResponseEntity<String> response = commandBus.send(command);
       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "UtenteController", "inativarUtente");
        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
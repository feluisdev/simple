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
import cv.igrp.simple.utente.application.dto.ServicoResponseDTO;
import cv.igrp.simple.utente.application.dto.AdicionarServicoDTO;
import cv.igrp.simple.utente.application.dto.ServicoAssociadoResponseDTO;

@IgrpController
@RestController
@RequestMapping(path = "utentes/v1")
@Tag(name = "Servico", description = "serviços de um utente")
public class ServicoController {

   private static final Logger LOGGER = LoggerFactory.getLogger(ServicoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public ServicoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
    value = "/{utenteId}/servicos"
  )
  @Operation(
    summary = "GET method to handle operations for listaServicosUtente",
    description = "GET method to handle operations for listaServicosUtente",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = ServicoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<Page<ServicoResponseDTO>> listaServicosUtente(
    @RequestParam(value = "Tipo", required = false) String Tipo,
    @RequestParam(value = "Estado", required = false) String Estado,
    @RequestParam(value = "dataInicio", required = false) String dataInicio,
    @RequestParam(value = "dataFIm", required = false) String dataFIm, @PathVariable(value = "utenteId") String utenteId,@ParameterObject Pageable pageable)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "ServicoController", "listaServicosUtente");
      final var query = new ListaServicosUtenteQuery(Tipo, Estado, dataInicio, dataFIm, utenteId,pageable);
      ResponseEntity<Page<ServicoResponseDTO>> response = queryBus.handle(query);
      LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "ServicoController", "listaServicosUtente");
      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
    value = "{utenteId}/servicos/{servicoId}"
  )
  @Operation(
    summary = "POST method to handle operations for adicionarServicoUtente",
    description = "POST method to handle operations for adicionarServicoUtente",
    responses = {
      @ApiResponse(
          responseCode = "201",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = ServicoAssociadoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<ServicoAssociadoResponseDTO> adicionarServicoUtente(@Valid @RequestBody AdicionarServicoDTO adicionarServicoUtenteRequest
    , @PathVariable(value = "utenteId") Integer utenteId,@PathVariable(value = "servicoId") Integer servicoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "ServicoController", "adicionarServicoUtente");
      final var command = new AdicionarServicoUtenteCommand(adicionarServicoUtenteRequest, utenteId, servicoId);
       ResponseEntity<ServicoAssociadoResponseDTO> response = commandBus.send(command);
       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "ServicoController", "adicionarServicoUtente");
        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{utenteId}/servicos/{servicoId}"
  )
  @Operation(
    summary = "GET method to handle operations for obterDetalhesServico",
    description = "GET method to handle operations for obterDetalhesServico",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = ServicoResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<ServicoResponseDTO> obterDetalhesServico(
    @PathVariable(value = "utenteId") Integer utenteId,@PathVariable(value = "servicoId") Integer servicoId)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "ServicoController", "obterDetalhesServico");
      final var query = new ObterDetalhesServicoQuery(utenteId, servicoId);
      ResponseEntity<ServicoResponseDTO> response = queryBus.handle(query);
      LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "ServicoController", "obterDetalhesServico");
      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
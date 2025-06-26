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


import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import cv.igrp.simple.configuracoes.application.dto.ConfiguracoesResponseDTO;

@IgrpController
@RestController
@RequestMapping(path = "configuracoes/v1")
@Tag(name = "Configuracoes", description = "endpoint para gestao de configuracoes")
public class ConfiguracoesController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracoesController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public ConfiguracoesController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for listaDeConfiguracoes",
    description = "GET method to handle operations for listaDeConfiguracoes",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = ConfiguracoesResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<Page<ConfiguracoesResponseDTO>> listaDeConfiguracoes(
    @RequestParam(value = "chave", required = false) String chave,
    @RequestParam(value = "grupo", required = false) String grupo,
    @RequestParam(value = "tipo", required = false) String tipo,
    @RequestParam(value = "estado", required = false) String estado,@ParameterObject Pageable pageable)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "ConfiguracoesController", "listaDeConfiguracoes");
      final var query = new ListaDeConfiguracoesQuery(chave, grupo, tipo, estado,pageable);

      ResponseEntity<Page<ConfiguracoesResponseDTO>> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
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


import cv.igrp.simple.configuracoes.application.dto.CriarCategoriasServicosDTO;
import java.util.Map;
import cv.igrp.simple.configuracoes.application.dto.WrapperListaCategoriaServicoDTO;

@IgrpController
@RestController
@RequestMapping(path = "configuracoes/v1/categoriaservico")
@Tag(name = "CategoriaServico", description = "categoria servico management")
public class CategoriaServicoController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaServicoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public CategoriaServicoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createCategoria",
    description = "POST method to handle operations for createCategoria",
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
  
  public ResponseEntity<Map<String, ?>> createCategoria(@Valid @RequestBody CriarCategoriasServicosDTO createCategoriaRequest
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "CategoriaServicoController", "createCategoria");
      final var command = new CreateCategoriaCommand(createCategoriaRequest);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "CategoriaServicoController", "createCategoria");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for listaCategoriaServico",
    description = "GET method to handle operations for listaCategoriaServico",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListaCategoriaServicoDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListaCategoriaServicoDTO> listaCategoriaServico(
    @RequestParam(value = "nome", required = false) String nome,
    @RequestParam(value = "pagina", required = false, defaultValue = "0") String pagina,
    @RequestParam(value = "tamanho", required = false, defaultValue = "20") String tamanho)
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "CategoriaServicoController", "listaCategoriaServico");
      final var query = new ListaCategoriaServicoQuery(nome, pagina, tamanho);

      ResponseEntity<WrapperListaCategoriaServicoDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
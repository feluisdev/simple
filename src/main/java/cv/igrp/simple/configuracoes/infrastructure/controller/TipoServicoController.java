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


import cv.igrp.simple.configuracoes.application.dto.CriarTiposServicosDTO;
import java.util.Map;

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

}
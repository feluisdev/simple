package cv.igrp.simple.pedido.infrastructure.controller;

import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.framework.stereotype.IgrpController;
import cv.igrp.simple.pedido.application.commands.CreateEtapaProcessoCommand;
import cv.igrp.simple.pedido.application.dto.EtapaProcessoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "etapaprocesso")
@Tag(name = "EtapaProcesso", description = "demo")
public class EtapaProcessoController {

   private static final Logger LOGGER = LoggerFactory.getLogger(EtapaProcessoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public EtapaProcessoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createEtapaProcesso",
    description = "POST method to handle operations for createEtapaProcesso",
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
  
  public ResponseEntity<Map<String, ?>> createEtapaProcesso(@Valid @RequestBody EtapaProcessoRequestDTO createEtapaProcessoRequest
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "EtapaProcessoController", "createEtapaProcesso");
      final var command = new CreateEtapaProcessoCommand(createEtapaProcessoRequest);
       ResponseEntity<Map<String, ?>> response = commandBus.send(command);
       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "EtapaProcessoController", "createEtapaProcesso");
        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
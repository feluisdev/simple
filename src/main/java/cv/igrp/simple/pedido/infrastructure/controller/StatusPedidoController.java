package cv.igrp.simple.pedido.infrastructure.controller;

import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.framework.stereotype.IgrpController;
import cv.igrp.simple.pedido.application.commands.CreateStatusPedidoCommand;
import cv.igrp.simple.pedido.application.dto.StatusPedidoRequestDTO;
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
@RequestMapping(path = "status")
@Tag(name = "StatusPedido", description = "status management")
public class StatusPedidoController {

   private static final Logger LOGGER = LoggerFactory.getLogger(StatusPedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public StatusPedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createStatusPedido",
    description = "POST method to handle operations for createStatusPedido",
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
  
  public ResponseEntity<Map<String, ?>> createStatusPedido(@Valid @RequestBody StatusPedidoRequestDTO createStatusPedidoRequest
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "StatusPedidoController", "createStatusPedido");
      final var command = new CreateStatusPedidoCommand(createStatusPedidoRequest);
       ResponseEntity<Map<String, ?>> response = commandBus.send(command);
       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "StatusPedidoController", "createStatusPedido");
        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
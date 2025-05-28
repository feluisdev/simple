package cv.igrp.simple.pedido.infrastructure.controller;

import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.framework.stereotype.IgrpController;
import cv.igrp.simple.pedido.application.commands.CreatePedidoCommand;
import cv.igrp.simple.pedido.application.dto.PedidoRequestDTO;
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
@RequestMapping(path = "pedido")
@Tag(name = "Pedido", description = "demo")
public class PedidoController {

   private static final Logger LOGGER = LoggerFactory.getLogger(PedidoController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public PedidoController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createPedido",
    description = "POST method to handle operations for createPedido",
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
  
  public ResponseEntity<Map<String, ?>> createPedido(@Valid @RequestBody PedidoRequestDTO createPedidoRequest
    )
  {
      LOGGER.debug("Operation started - Endpoint: {}, Action: {}", "PedidoController", "createPedido");
      final var command = new CreatePedidoCommand(createPedidoRequest);
       ResponseEntity<Map<String, ?>> response = commandBus.send(command);
       LOGGER.debug("Operation finished - Endpoint: {}, Action: {}", "PedidoController", "createPedido");
        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
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


import cv.igrp.simple.licenciamento.application.dto.WrapperListaClasseDTO;
import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.ClasseRequestDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/classes")
@Tag(name = "Classe", description = "gestao de classes")
public class ClasseController {

  private static final Logger LOGGER = LoggerFactory.getLogger(ClasseController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public ClasseController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for getClasses",
    description = "GET method to handle operations for getClasses",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListaClasseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListaClasseDTO> getClasses(
    @RequestParam(value = "classe", required = false) String classe,
    @RequestParam(value = "descricao", required = false) String descricao,
    @RequestParam(value = "estado", required = false) String estado,
    @RequestParam(value = "pagina", defaultValue = "0") String pagina,
    @RequestParam(value = "tamanho", defaultValue = "20") String tamanho)
  {

      LOGGER.debug("Operation started");

      final var query = new GetClassesQuery(classe, descricao, estado, pagina, tamanho);

      ResponseEntity<WrapperListaClasseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{idClasse}"
  )
  @Operation(
    summary = "GET method to handle operations for getClasseById",
    description = "GET method to handle operations for getClasseById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = ClasseResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<ClasseResponseDTO> getClasseById(
    @PathVariable(value = "idClasse") String idClasse)
  {

      LOGGER.debug("Operation started");

      final var query = new GetClasseByIdQuery(idClasse);

      ResponseEntity<ClasseResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{classeId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateClasse",
    description = "PUT method to handle operations for updateClasse",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = ClasseResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<ClasseResponseDTO> updateClasse(@Valid @RequestBody ClasseRequestDTO updateClasseRequest
    , @PathVariable(value = "classeId") String classeId)
  {

      LOGGER.debug("Operation started");

      final var command = new UpdateClasseCommand(updateClasseRequest, classeId);

       ResponseEntity<ClasseResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createClasse",
    description = "POST method to handle operations for createClasse",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = ClasseResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<ClasseResponseDTO> createClasse(@Valid @RequestBody ClasseRequestDTO createClasseRequest
    )
  {

      LOGGER.debug("Operation started");

      final var command = new CreateClasseCommand(createClasseRequest);

       ResponseEntity<ClasseResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{idClasse}/ativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for ativarClasse",
    description = "PATCH method to handle operations for ativarClasse",
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
  
  public ResponseEntity<Map<String, ?>> ativarClasse(
    @PathVariable(value = "idClasse") String idClasse)
  {

      LOGGER.debug("Operation started");

      final var command = new AtivarClasseCommand(idClasse);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{idClasse}/desativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for desativarClasse",
    description = "PATCH method to handle operations for desativarClasse",
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
  
  public ResponseEntity<Map<String, ?>> desativarClasse(
    @PathVariable(value = "idClasse") String idClasse)
  {

      LOGGER.debug("Operation started");

      final var command = new DesativarClasseCommand(idClasse);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
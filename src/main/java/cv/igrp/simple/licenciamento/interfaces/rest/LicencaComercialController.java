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


import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.WrapperListaLicencaComercialDTO;
import cv.igrp.simple.licenciamento.application.dto.LicencaRequestDTO;
import cv.igrp.simple.licenciamento.application.dto.LicencaResponseLigthDTO;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/licencas")
@Tag(name = "LicencaComercial", description = "gestao de licencas comerciais")
public class LicencaComercialController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LicencaComercialController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public LicencaComercialController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
    value = "{idLicenca}"
  )
  @Operation(
    summary = "GET method to handle operations for getLicencaComercialByID",
    description = "GET method to handle operations for getLicencaComercialByID",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = LicencaResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<LicencaResponseDTO> getLicencaComercialByID(
    @PathVariable(value = "idLicenca") String idLicenca)
  {

      LOGGER.debug("Operation started");

      final var query = new GetLicencaComercialByIDQuery(idLicenca);

      ResponseEntity<LicencaResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for getLicencasComerciais",
    description = "GET method to handle operations for getLicencasComerciais",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListaLicencaComercialDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListaLicencaComercialDTO> getLicencasComerciais(
    @RequestParam(value = "alvara", required = false) String alvara,
    @RequestParam(value = "EstadoLicenca", required = false) String EstadoLicenca,
    @RequestParam(value = "idEstabelecimento", required = false) String idEstabelecimento,
    @RequestParam(value = "pagina", defaultValue = "0") String pagina,
    @RequestParam(value = "tamanho", defaultValue = "20") String tamanho)
  {

      LOGGER.debug("Operation started");

      final var query = new GetLicencasComerciaisQuery(alvara, EstadoLicenca, idEstabelecimento, pagina, tamanho);

      ResponseEntity<WrapperListaLicencaComercialDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createLicencaComercial",
    description = "POST method to handle operations for createLicencaComercial",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = LicencaResponseLigthDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<LicencaResponseLigthDTO> createLicencaComercial(@Valid @RequestBody LicencaRequestDTO createLicencaComercialRequest
    )
  {

      LOGGER.debug("Operation started");

      final var command = new CreateLicencaComercialCommand(createLicencaComercialRequest);

       ResponseEntity<LicencaResponseLigthDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{idLicenca}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateLicencaComercial",
    description = "PUT method to handle operations for updateLicencaComercial",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = LicencaResponseLigthDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<LicencaResponseLigthDTO> updateLicencaComercial(@Valid @RequestBody LicencaRequestDTO updateLicencaComercialRequest
    , @PathVariable(value = "idLicenca") String idLicenca)
  {

      LOGGER.debug("Operation started");

      final var command = new UpdateLicencaComercialCommand(updateLicencaComercialRequest, idLicenca);

       ResponseEntity<LicencaResponseLigthDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
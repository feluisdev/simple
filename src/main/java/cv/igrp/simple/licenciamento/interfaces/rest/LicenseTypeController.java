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


import cv.igrp.simple.licenciamento.application.dto.WrapperListLicenseTypeDTO;
import cv.igrp.simple.licenciamento.application.dto.LicenseTypeResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.LicencaRequestDTO;
import cv.igrp.simple.licenciamento.application.dto.LicenseTypeRequestDTO;
import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/license-types")
@Tag(name = "LicenseType", description = "gestao de tipos licenciamento")
public class LicenseTypeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(LicenseTypeController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public LicenseTypeController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for getLicenseTypes",
    description = "GET method to handle operations for getLicenseTypes",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListLicenseTypeDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListLicenseTypeDTO> getLicenseTypes(
    @RequestParam(value = "categoryId", required = false) String categoryId,
    @RequestParam(value = "licensingModel", required = false) String licensingModel,
    @RequestParam(value = "active", required = false) boolean active,
    @RequestParam(value = "renewable", required = false) boolean renewable,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "code", required = false) String code,
    @RequestParam(value = "pageNumber") String pageNumber,
    @RequestParam(value = "pageSize") String pageSize)
  {

      LOGGER.debug("Operation started");

      final var query = new GetLicenseTypesQuery(categoryId, licensingModel, active, renewable, name, code, pageNumber, pageSize);

      ResponseEntity<WrapperListLicenseTypeDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{licenseTypeId}"
  )
  @Operation(
    summary = "GET method to handle operations for getLicenseTypeById",
    description = "GET method to handle operations for getLicenseTypeById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = LicenseTypeResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<LicenseTypeResponseDTO> getLicenseTypeById(
    @PathVariable(value = "licenseTypeId") String licenseTypeId)
  {

      LOGGER.debug("Operation started");

      final var query = new GetLicenseTypeByIdQuery(licenseTypeId);

      ResponseEntity<LicenseTypeResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createLicenseType",
    description = "POST method to handle operations for createLicenseType",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = LicenseTypeResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<LicenseTypeResponseDTO> createLicenseType(@Valid @RequestBody LicencaRequestDTO createLicenseTypeRequest
    )
  {

      LOGGER.debug("Operation started");

      final var command = new CreateLicenseTypeCommand(createLicenseTypeRequest);

       ResponseEntity<LicenseTypeResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{licenseTypeId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateLicenseType",
    description = "PUT method to handle operations for updateLicenseType",
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
  
  public ResponseEntity<LicencaResponseDTO> updateLicenseType(@Valid @RequestBody LicenseTypeRequestDTO updateLicenseTypeRequest
    , @PathVariable(value = "licenseTypeId") String licenseTypeId)
  {

      LOGGER.debug("Operation started");

      final var command = new UpdateLicenseTypeCommand(updateLicenseTypeRequest, licenseTypeId);

       ResponseEntity<LicencaResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{licenseTypeId}/ativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for ativarLicenseType",
    description = "PATCH method to handle operations for ativarLicenseType",
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
  
  public ResponseEntity<Map<String, ?>> ativarLicenseType(
    @PathVariable(value = "licenseTypeId") String licenseTypeId)
  {

      LOGGER.debug("Operation started");

      final var command = new AtivarLicenseTypeCommand(licenseTypeId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{licenseTypeId}/desativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for desativarLicenseType",
    description = "PATCH method to handle operations for desativarLicenseType",
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
  
  public ResponseEntity<Map<String, ?>> desativarLicenseType(
    @PathVariable(value = "licenseTypeId") String licenseTypeId)
  {

      LOGGER.debug("Operation started");

      final var command = new DesativarLicenseTypeCommand(licenseTypeId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
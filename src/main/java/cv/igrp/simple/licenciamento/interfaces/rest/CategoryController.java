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


import cv.igrp.simple.licenciamento.application.dto.WrapperListCategoryDTO;
import cv.igrp.simple.licenciamento.application.dto.CategoryResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.CategoryRequestDTO;
import cv.igrp.simple.licenciamento.application.dto.MoveCategoryDTO;
import java.util.Map;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/categories")
@Tag(name = "Category", description = "gestao Categorias licenciamento")
public class CategoryController {

  private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

  
  private final CommandBus commandBus;
  private final QueryBus queryBus;

  
  public CategoryController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for getListCategoriasLicenciamento",
    description = "GET method to handle operations for getListCategoriasLicenciamento",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListCategoryDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<WrapperListCategoryDTO> getListCategoriasLicenciamento(
    @RequestParam(value = "sectorId", required = false) String sectorId,
    @RequestParam(value = "parentId", required = false) String parentId,
    @RequestParam(value = "level", required = false) Integer level,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "code", required = false) String code,
    @RequestParam(value = "active", required = false, defaultValue = "true") boolean active,
    @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber,
    @RequestParam(value = "pageSize", defaultValue = "20") String pageSize)
  {

      LOGGER.debug("Operation started");

      final var query = new GetListCategoriasLicenciamentoQuery(sectorId, parentId, level, name, code, active, pageNumber, pageSize);

      ResponseEntity<WrapperListCategoryDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{categoryId}"
  )
  @Operation(
    summary = "GET method to handle operations for getCategoryLicenciamentoById",
    description = "GET method to handle operations for getCategoryLicenciamentoById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = CategoryResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<CategoryResponseDTO> getCategoryLicenciamentoById(
    @PathVariable(value = "categoryId") String categoryId)
  {

      LOGGER.debug("Operation started");

      final var query = new GetCategoryLicenciamentoByIdQuery(categoryId);

      ResponseEntity<CategoryResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createCategoryLicenciamento",
    description = "POST method to handle operations for createCategoryLicenciamento",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = CategoryResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<CategoryResponseDTO> createCategoryLicenciamento(@Valid @RequestBody CategoryRequestDTO createCategoryLicenciamentoRequest
    )
  {

      LOGGER.debug("Operation started");

      final var command = new CreateCategoryLicenciamentoCommand(createCategoryLicenciamentoRequest);

       ResponseEntity<CategoryResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{categoryId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateCategoryLicenciamento",
    description = "PUT method to handle operations for updateCategoryLicenciamento",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = CategoryResponseDTO.class,
                  type = "object")
          )
      )
    }
  )
  
  public ResponseEntity<CategoryResponseDTO> updateCategoryLicenciamento(@Valid @RequestBody CategoryRequestDTO updateCategoryLicenciamentoRequest
    , @PathVariable(value = "categoryId") String categoryId)
  {

      LOGGER.debug("Operation started");

      final var command = new UpdateCategoryLicenciamentoCommand(updateCategoryLicenciamentoRequest, categoryId);

       ResponseEntity<CategoryResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{categoryId}/move"
  )
  @Operation(
    summary = "PATCH method to handle operations for moveCategoryLicenciamento",
    description = "PATCH method to handle operations for moveCategoryLicenciamento",
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
  
  public ResponseEntity<Map<String, ?>> moveCategoryLicenciamento(@Valid @RequestBody MoveCategoryDTO moveCategoryLicenciamentoRequest
    , @PathVariable(value = "categoryId") String categoryId)
  {

      LOGGER.debug("Operation started");

      final var command = new MoveCategoryLicenciamentoCommand(moveCategoryLicenciamentoRequest, categoryId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{categoryId}/ativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for ativarCategoriaLicenciamento",
    description = "PATCH method to handle operations for ativarCategoriaLicenciamento",
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
  
  public ResponseEntity<Map<String, ?>> ativarCategoriaLicenciamento(
    @PathVariable(value = "categoryId") String categoryId)
  {

      LOGGER.debug("Operation started");

      final var command = new AtivarCategoriaLicenciamentoCommand(categoryId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{categoryId}/desativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for desativarCategoryLicenciamento",
    description = "PATCH method to handle operations for desativarCategoryLicenciamento",
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
  
  public ResponseEntity<Map<String, ?>> desativarCategoryLicenciamento(
    @PathVariable(value = "categoryId") String categoryId)
  {

      LOGGER.debug("Operation started");

      final var command = new DesativarCategoryLicenciamentoCommand(categoryId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
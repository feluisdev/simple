package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.license2.models.Category;
import cv.igrp.simple.licenciamento.domain.license2.repository.CategoryRepository;
import cv.igrp.simple.licenciamento.domain.license2.repository.SectorRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.CategoryMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.CategoryResponseDTO;

@Component
public class CreateCategoryLicenciamentoCommandHandler implements CommandHandler<CreateCategoryLicenciamentoCommand, ResponseEntity<CategoryResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateCategoryLicenciamentoCommandHandler.class);

   private final CategoryRepository categoryRepository;
   private final CategoryMapper categoryMapper;
   private final SectorRepository sectorRepository;

   public CreateCategoryLicenciamentoCommandHandler(CategoryRepository categoryRepository, CategoryMapper categoryMapper, SectorRepository sectorRepository) {

       this.categoryRepository = categoryRepository;
       this.categoryMapper = categoryMapper;
       this.sectorRepository = sectorRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<CategoryResponseDTO> handle(CreateCategoryLicenciamentoCommand command) {
      var dto = command.getCategoryrequest();

      // 1. Verificar código único
      if (categoryRepository.existsByCode(dto.getCode())) {
         throw IgrpResponseStatusException.badRequest(
                 "Category with code '" + dto.getCode() + "' already exists");
      }

      var sector = sectorRepository.findById(Identificador.from(dto.getSectorId()))
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Sector not found"));

      Identificador parentId = dto.getParentId() != null ? Identificador.from(dto.getParentId()) : null;

      var metadata = Metadata.fromMap(dto.getMetadata());

      // 4. Criar categoria
      var category = Category.criarNovo(
              dto.getName(),
              dto.getDescription(),
              dto.getCode(),
              0, // todo level inicial, pode ser calculado depois
              dto.getSortOrder(),
              metadata,
              "", // todo path inicial, pode atualizar depois
              parentId,
              sector
      );

      // 5. Persistir
      var savedCategory = categoryRepository.save(category);

      // 6. Mapear para DTO de resposta
      var responseDTO = categoryMapper.toDTO(savedCategory);

      return ResponseEntity.ok(responseDTO);

   }

}
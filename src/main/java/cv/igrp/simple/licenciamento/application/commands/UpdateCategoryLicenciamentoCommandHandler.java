package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
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
public class UpdateCategoryLicenciamentoCommandHandler implements CommandHandler<UpdateCategoryLicenciamentoCommand, ResponseEntity<CategoryResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoryLicenciamentoCommandHandler.class);

   private final CategoryRepository categoryRepository;
   private final CategoryMapper categoryMapper;
   private final SectorRepository sectorRepository;

   public UpdateCategoryLicenciamentoCommandHandler(CategoryRepository categoryRepository, CategoryMapper categoryMapper, SectorRepository sectorRepository) {

       this.categoryRepository = categoryRepository;
       this.categoryMapper = categoryMapper;
       this.sectorRepository = sectorRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<CategoryResponseDTO> handle(UpdateCategoryLicenciamentoCommand command) {
      var dto = command.getCategoryrequest();
      var categoryId = Identificador.from(command.getCategoryId());

      // 1. Buscar a categoria existente
      var category = categoryRepository.findById(categoryId)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Category not found"));

      // 2. Verificar se o código é único (exceto a própria categoria)
      if (!category.getCode().equals(dto.getCode()) && categoryRepository.existsByCode(dto.getCode())) {
         throw IgrpResponseStatusException.badRequest(
                 "Category with code '" + dto.getCode() + "' already exists");
      }

      // 3. Recuperar o setor, se fornecido
      var sector = sectorRepository.findById(Identificador.from(dto.getSectorId()))
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Sector not found"));

      // 4. Metadata
      var metadata = Metadata.fromMap(dto.getMetadata());

      // 5. Atualizar categoria
      category.atualizar(
              dto.getName(),
              dto.getDescription(),
              dto.getCode(),
              category.getLevel(), // manter level atual
              dto.getSortOrder(),
              metadata,
              category.getPath(),  // manter path atual
              dto.getParentId() != null ? Identificador.from(dto.getParentId()) : null,
              sector
      );

      // 6. Persistir
      var savedCategory = categoryRepository.save(category);

      // 7. Mapear para DTO de resposta
      var responseDTO = categoryMapper.toDTO(savedCategory);

      return ResponseEntity.ok(responseDTO);
   }

}
package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.license2.repository.CategoryRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class MoveCategoryLicenciamentoCommandHandler implements CommandHandler<MoveCategoryLicenciamentoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(MoveCategoryLicenciamentoCommandHandler.class);

   private final CategoryRepository categoryRepository;

   public MoveCategoryLicenciamentoCommandHandler(CategoryRepository categoryRepository) {

       this.categoryRepository = categoryRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(MoveCategoryLicenciamentoCommand command) {
      var categoryId = Identificador.from(command.getCategoryId());

      var category = categoryRepository.findById(categoryId)
              .orElseThrow(() -> IgrpResponseStatusException.notFound(
                      "Category with ID '" + command.getCategoryId() + "' not found"));

      Identificador newParentId = command.getMovecategory().getNewParentId() != null
              ? Identificador.from(command.getMovecategory().getNewParentId())
              : null;

      category.move(newParentId);

      categoryRepository.save(category);

      return ResponseEntity.ok(Map.of("message", "Category moved successfully"));
   }

}
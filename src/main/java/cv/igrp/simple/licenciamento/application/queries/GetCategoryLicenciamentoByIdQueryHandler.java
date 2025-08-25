package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.license2.repository.CategoryRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.CategoryMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.CategoryResponseDTO;

@Component
public class GetCategoryLicenciamentoByIdQueryHandler implements QueryHandler<GetCategoryLicenciamentoByIdQuery, ResponseEntity<CategoryResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoryLicenciamentoByIdQueryHandler.class);

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;


  public GetCategoryLicenciamentoByIdQueryHandler(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {

      this.categoryRepository = categoryRepository;
      this.categoryMapper = categoryMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<CategoryResponseDTO> handle(GetCategoryLicenciamentoByIdQuery query) {
     var categoryId = Identificador.from(query.getCategoryId());

     var category = categoryRepository.findById(categoryId)
             .orElseThrow(() -> IgrpResponseStatusException.notFound(
                     "Category with ID '" + query.getCategoryId() + "' not found"));

     var responseDTO = categoryMapper.toDTO(category);

     return ResponseEntity.ok(responseDTO);
  }

}
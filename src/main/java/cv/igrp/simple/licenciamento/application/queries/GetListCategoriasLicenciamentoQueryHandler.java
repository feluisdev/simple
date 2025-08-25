package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.license2.filter.CategoryFilter;
import cv.igrp.simple.licenciamento.domain.license2.repository.CategoryRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.CategoryMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.WrapperListCategoryDTO;

@Component
public class GetListCategoriasLicenciamentoQueryHandler implements QueryHandler<GetListCategoriasLicenciamentoQuery, ResponseEntity<WrapperListCategoryDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetListCategoriasLicenciamentoQueryHandler.class);


  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public GetListCategoriasLicenciamentoQueryHandler(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {

      this.categoryRepository = categoryRepository;
      this.categoryMapper = categoryMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListCategoryDTO> handle(GetListCategoriasLicenciamentoQuery query) {
    LOGGER.info("Handling GetListCategoriasLicenciamentoQuery: {}", query);
     var filter = CategoryFilter.builder()
             .sectorId(query.getSectorId() != null ? Identificador.from(query.getSectorId()) : null)
             .parentId(query.getParentId() != null ? Identificador.from(query.getParentId()) : null)
             .level(query.getLevel())
             .name(query.getName())
             .code(query.getCode())
             .active(query.isActive())
             .pageNumber(query.getPageNumber() != null ? Integer.parseInt(query.getPageNumber()) : 0)
             .pageSize(query.getPageSize() != null ? Integer.parseInt(query.getPageSize()) : 20)
             .build();

     var categories = categoryRepository.findAll(filter);

     var content = categories.stream()
             .map(categoryMapper::toDTO)
             .toList();

     long totalElements = content.size();

     int pageNumber = filter.getPageNumber() != null ? filter.getPageNumber() : 0;
     int pageSize = filter.getPageSize() != null ? filter.getPageSize() : 20;
     int totalPages = (int) Math.ceil((double) totalElements / pageSize);

     // 5. Montar wrapper
     var wrapper = new WrapperListCategoryDTO();
     wrapper.setContent(content);
     wrapper.setPageNumber(pageNumber);
     wrapper.setPageSize(pageSize);
     wrapper.setTotalElements(totalElements);
     wrapper.setTotalPages(totalPages);
     wrapper.setFirst(pageNumber == 0);
     wrapper.setLast(pageNumber + 1 >= totalPages);

     return ResponseEntity.ok(wrapper);
  }

}
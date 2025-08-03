package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;
import cv.igrp.simple.licenciamento.domain.filter.ClasseFilter;
import cv.igrp.simple.licenciamento.domain.models.Classe;
import cv.igrp.simple.licenciamento.domain.repository.ClasseRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.ClasseMapper;
import cv.igrp.simple.shared.application.constants.Estado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.WrapperListaClasseDTO;

import java.util.List;

@Component
public class GetClassesQueryHandler implements QueryHandler<GetClassesQuery, ResponseEntity<WrapperListaClasseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetClassesQueryHandler.class);


  private final ClasseRepository classeRepository;
  private final ClasseMapper classeMapper;

  public GetClassesQueryHandler(ClasseRepository classeRepository, ClasseMapper classeMapper) {

      this.classeRepository = classeRepository;
      this.classeMapper = classeMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaClasseDTO> handle(GetClassesQuery query) {
     var filter = ClasseFilter.builder()
             .classe(query.getClasse())
             .descricao(query.getDescricao())
             .estado(query.getEstado() != null ? Estado.valueOf(query.getEstado()) : null)
             .pageNumber(Integer.parseInt(query.getPagina()))
             .pageSize(Integer.parseInt(query.getTamanho()))
             .build();

     List<Classe> classes = classeRepository.findAll(filter);

     List<ClasseResponseDTO> contentDTO = classes.stream()
             .map(classeMapper::toDTO)
             .toList();


     WrapperListaClasseDTO response = new WrapperListaClasseDTO();
     response.setContent(contentDTO);
     response.setPageNumber(filter.getPageNumber());
     response.setPageSize(filter.getPageSize());
     response.setTotalElements((long) contentDTO.size());

     return ResponseEntity.ok(response);
  }

}
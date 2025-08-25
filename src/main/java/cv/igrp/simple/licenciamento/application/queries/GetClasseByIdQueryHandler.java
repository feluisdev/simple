package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.ClasseRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.ClasseMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;

@Component
public class GetClasseByIdQueryHandler implements QueryHandler<GetClasseByIdQuery, ResponseEntity<ClasseResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetClasseByIdQueryHandler.class);

  private final ClasseRepository classeRepository;
  private final ClasseMapper classeMapper;

  public GetClasseByIdQueryHandler(ClasseRepository classeRepository, ClasseMapper classeMapper) {

      this.classeRepository = classeRepository;
      this.classeMapper = classeMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<ClasseResponseDTO> handle(GetClasseByIdQuery query) {
     var id = Identificador.from(query.getIdClasse());

     var classe = classeRepository.findById(id)
             .orElseThrow(() -> IgrpResponseStatusException.notFound("Classe não encontrada: " + query.getIdClasse()));

     var dto = classeMapper.toDTO(classe);

     return ResponseEntity.ok(dto);
  }

}
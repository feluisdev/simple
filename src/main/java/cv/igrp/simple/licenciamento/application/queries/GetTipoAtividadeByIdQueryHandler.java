package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.TipoAtividadeRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.TipoAtividadeMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeResponseDTO;

@Component
public class GetTipoAtividadeByIdQueryHandler implements QueryHandler<GetTipoAtividadeByIdQuery, ResponseEntity<TipoAtividadeResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetTipoAtividadeByIdQueryHandler.class);


  private final TipoAtividadeRepository repository;
  private final TipoAtividadeMapper mapper;


  public GetTipoAtividadeByIdQueryHandler(TipoAtividadeRepository repository, TipoAtividadeMapper mapper) {

      this.repository = repository;
      this.mapper = mapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<TipoAtividadeResponseDTO> handle(GetTipoAtividadeByIdQuery query) {
     var id = Identificador.from(query.getIdTipoAtividade());

     var tipoAtividade = repository.findById(id)
             .orElseThrow(() -> IgrpResponseStatusException.notFound("Tipo de atividade não encontrado"));

     var dto = mapper.toDTO(tipoAtividade);
     return ResponseEntity.ok(dto);
  }

}
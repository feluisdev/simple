package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.EstabelecimentoRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.EstabelecimentoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseDTO;

@Component
public class GetEstabelecimentoByIdQueryHandler implements QueryHandler<GetEstabelecimentoByIdQuery, ResponseEntity<EstabelecimentoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetEstabelecimentoByIdQueryHandler.class);

  private final EstabelecimentoRepository repository;
  private final EstabelecimentoMapper mapper;

  public GetEstabelecimentoByIdQueryHandler(EstabelecimentoRepository repository, EstabelecimentoMapper mapper) {

      this.repository = repository;
      this.mapper = mapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<EstabelecimentoResponseDTO> handle(GetEstabelecimentoByIdQuery query) {

     var id = Identificador.from(query.getIdEstabelecimento());

     var estabelecimento = repository.findById(id)
             .orElseThrow(() -> IgrpResponseStatusException.notFound("Estabelecimento nao encontrado"));

     EstabelecimentoResponseDTO dto = mapper.toDTO(estabelecimento);
     return ResponseEntity.ok(dto);
   }


}
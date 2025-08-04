package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.domain.repository.LicencaComercialRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.LicencaComercialMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;

@Component
public class GetLicencaComercialByIDQueryHandler implements QueryHandler<GetLicencaComercialByIDQuery, ResponseEntity<LicencaResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetLicencaComercialByIDQueryHandler.class);

  private final LicencaComercialRepository repository;
  private final LicencaComercialMapper mapper;

  public GetLicencaComercialByIDQueryHandler(LicencaComercialRepository repository, LicencaComercialMapper mapper) {

      this.repository = repository;
      this.mapper = mapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<LicencaResponseDTO> handle(GetLicencaComercialByIDQuery query) {
     var id = Identificador.from(query.getIdLicenca());

     var licenca = repository.findById(id)
             .orElseThrow(() -> IgrpResponseStatusException.notFound("Licença comercial não encontrada"));

     LicencaResponseDTO dto = mapper.toDTO(licenca);
     return ResponseEntity.ok(dto);
  }

}
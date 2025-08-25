package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.license2.repository.SectorRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.SectorMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.SectorResponseDTO;

@Component
public class UpdateSectorCommandHandler implements CommandHandler<UpdateSectorCommand, ResponseEntity<SectorResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateSectorCommandHandler.class);

   private final SectorRepository sectorRepository;
   private final SectorMapper sectorMapper;

   public UpdateSectorCommandHandler(SectorRepository sectorRepository, SectorMapper sectorMapper) {

       this.sectorRepository = sectorRepository;
       this.sectorMapper = sectorMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<SectorResponseDTO> handle(UpdateSectorCommand command) {
      var dto = command.getSectorrequest();
      var sectorId = command.getSectorId();

      // 1. Validar se o ID do setor foi informado
      if (sectorId == null || sectorId.isBlank()) {
         throw IgrpResponseStatusException.badRequest("The field <sectorId> is required");
      }

      var existingSector = sectorRepository.findById(Identificador.from(sectorId))
              .orElseThrow(() -> IgrpResponseStatusException.notFound(
                      "Sector with id '" + sectorId + "' not found"));

     /* if (!existingSector.getCode().equals(dto.getCode()) && sectorRepository.existsByCode(dto.getCode())) {
         throw IgrpResponseStatusException.badRequest(
                 "Sector with code '" + dto.getCode() + "' already exists");
      }*/

      // 4. Atualizar metadata
      var metadata = Metadata.fromMap(dto.getMetadata());

      // 5. Atualizar dados do setor
      existingSector.atualizar(
              dto.getName(),
              dto.getDescription(),
              dto.getSectorTypeKey(),
              dto.getCode(),
              dto.getSortOrder(),
              metadata
      );

      // 6. Persistir via repositório
      var savedSector = sectorRepository.save(existingSector);

      // 7. Converter para DTO de resposta
      var responseDTO = sectorMapper.toResponseDTO(savedSector);

      return ResponseEntity.ok(responseDTO);
   }

}
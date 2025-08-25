package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.license2.models.Sector;
import cv.igrp.simple.licenciamento.domain.license2.repository.SectorRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.SectorMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.SectorResponseDTO;

import java.util.HashMap;
import java.util.Map;

@Component
public class CreateSectorCommandHandler implements CommandHandler<CreateSectorCommand, ResponseEntity<SectorResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateSectorCommandHandler.class);

   private final SectorRepository sectorRepository;
   private final SectorMapper sectorMapper;

   public CreateSectorCommandHandler(SectorRepository sectorRepository, SectorMapper sectorMapper) {

       this.sectorRepository = sectorRepository;
       this.sectorMapper = sectorMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<SectorResponseDTO> handle(CreateSectorCommand command) {
      var dto = command.getSectorrequest();

      // 1. Verificar se já existe setor com mesmo código
      if (sectorRepository.existsByCode(dto.getCode())) {
         throw IgrpResponseStatusException.badRequest(
                 "Sector with code '" + dto.getCode() + "' already exists");
      }

      Metadata metadata = Metadata.fromMap(dto.getMetadata());

      // 2. Criar o objeto de domínio
      var sector = Sector.criarNovo(
              dto.getName(),
              dto.getDescription(),
              dto.getSectorTypeKey(),
              dto.getCode(),
              dto.getSortOrder(),
              metadata
      );

      // 3. Persistir via repositório
      var savedSector = sectorRepository.save(sector);

      // 4. Converter para DTO de resposta
      var responseDTO = sectorMapper.toResponseDTO(savedSector);

      return ResponseEntity.ok(responseDTO);

   }

}
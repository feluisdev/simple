package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.application.dto.LicenseTypeResponseDTO;
import cv.igrp.simple.licenciamento.domain.license2.models.LicenseType;
import cv.igrp.simple.licenciamento.domain.license2.repository.LicenseTypeRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.LicenseTypeMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.domain.valueobject.Metadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;

@Component
public class UpdateLicenseTypeCommandHandler implements CommandHandler<UpdateLicenseTypeCommand, ResponseEntity<LicenseTypeResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateLicenseTypeCommandHandler.class);

   private final LicenseTypeRepository licenseTypeRepository;
   private final LicenseTypeMapper licenseTypeMapper;

   public UpdateLicenseTypeCommandHandler(LicenseTypeRepository licenseTypeRepository, LicenseTypeMapper licenseTypeMapper) {

       this.licenseTypeRepository = licenseTypeRepository;
       this.licenseTypeMapper = licenseTypeMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<LicenseTypeResponseDTO> handle(UpdateLicenseTypeCommand command) {
      var dto = command.getLicensetyperequest();
      Identificador licenseTypeId = Identificador.from(command.getLicenseTypeId());

      LicenseType existing = licenseTypeRepository.findById(licenseTypeId)
              .orElseThrow(() -> IgrpResponseStatusException.notFound(
                      "LicenseType not found for id: " + command.getLicenseTypeId()));

      // 2. Verificar código único caso tenha sido alterado
     /* if (!existing.getCode().equals(dto.getCode()) && licenseTypeRepository.existsByCode(dto.getCode())) {
         throw IgrpResponseStatusException.badRequest(
                 "LicenseType with code '" + dto.getCode() + "' already exists");
      }*/

      // 3. Metadata
      Metadata metadata = Metadata.fromMap(dto.getMetadata());

      // 4. Atualizar domínio
      existing.atualizar(
              dto.getName(),
              dto.getDescription(),
              dto.getCode(),
              dto.getLicensingModelKey(),
              dto.getValidityPeriod(),
              dto.getValidityUnitKey(),
              dto.isRenewable(),
              dto.isAutoRenewal(),
              dto.isRequiresInspection(),
              dto.isRequiresPublicConsultation(),
              dto.getMaxProcessingDays(),
              dto.isHasFees(),
              dto.getBaseFee(),
              dto.getCurrencyCode(),
              null, // todo : sortOrder, manter o existente ou recalcular se necessário
              metadata,
              Identificador.from(dto.getCategoryId())
      );

      LicenseType saved = licenseTypeRepository.save(existing);

      LicenseTypeResponseDTO responseDTO = licenseTypeMapper.toResponseDTO(saved);

      return ResponseEntity.ok(responseDTO);
   }

}
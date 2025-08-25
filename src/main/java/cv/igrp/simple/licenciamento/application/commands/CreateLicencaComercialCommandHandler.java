package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.models.LicencaComercial;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.EstabelecimentoRepository;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.LicencaComercialRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.LicencaComercialMapper;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.domain.repository.UtenteRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;

@Component
public class CreateLicencaComercialCommandHandler implements CommandHandler<CreateLicencaComercialCommand, ResponseEntity<LicencaResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateLicencaComercialCommandHandler.class);

   private final LicencaComercialRepository licencaComercialRepository;
   private final EstabelecimentoRepository estabelecimentoRepository;
   private final UtenteRepository utenteRepository;
   private final LicencaComercialMapper licencaComercialMapper;

   public CreateLicencaComercialCommandHandler(LicencaComercialRepository licencaComercialRepository, EstabelecimentoRepository estabelecimentoRepository, UtenteRepository utenteRepository, LicencaComercialMapper licencaComercialMapper) {

       this.licencaComercialRepository = licencaComercialRepository;
       this.estabelecimentoRepository = estabelecimentoRepository;
       this.utenteRepository = utenteRepository;
       this.licencaComercialMapper = licencaComercialMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<LicencaResponseDTO> handle(CreateLicencaComercialCommand command) {
      var dto = command.getLicencarequest();

      // Buscar Estabelecimento
      var estabelecimento = estabelecimentoRepository
              .findById(Identificador.from(dto.getIdEstabelecimento()))
              .orElseThrow(() -> IgrpResponseStatusException.badRequest("Estabelecimento não encontrado: " + dto.getIdEstabelecimento()));

      Utente utente = null;

      if (dto.getIdUtente() != null && !dto.getIdUtente().isBlank()) {
         var idUtente = Integer.parseInt(dto.getIdUtente());
         utente = utenteRepository
                 .findById(idUtente)
                 .orElseThrow(() -> IgrpResponseStatusException.badRequest("Utente não encontrado: " + dto.getIdUtente()));
      }


      // Criar licença comercial no domínio
      var licencaComercial = LicencaComercial.criarNovo(
              dto.getAlvara(),
              dto.getDataInicioLicenca(),
              dto.getDataFimLicenca(),
              dto.getDataRenovacaoLicenca(),
              dto.getHorarioInicioFuncionamento(),
              dto.getHorarioFimFuncionamento(),
              dto.getDesignacao(),
              dto.getEstadoLicenca(),
              utente,
              estabelecimento
      );

      LicencaComercial saved = licencaComercialRepository.save(licencaComercial);

      // Mapear para DTO
      LicencaResponseDTO responseDTO = licencaComercialMapper.toDTO(saved);

      return ResponseEntity.ok(responseDTO);
   }

}
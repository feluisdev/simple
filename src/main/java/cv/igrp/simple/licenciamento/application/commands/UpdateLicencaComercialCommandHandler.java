package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.models.Estabelecimento;
import cv.igrp.simple.licenciamento.domain.models.LicencaComercial;
import cv.igrp.simple.licenciamento.domain.repository.EstabelecimentoRepository;
import cv.igrp.simple.licenciamento.domain.repository.LicencaComercialRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.LicencaComercialMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.LicencaResponseDTO;

@Component
public class UpdateLicencaComercialCommandHandler implements CommandHandler<UpdateLicencaComercialCommand, ResponseEntity<LicencaResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateLicencaComercialCommandHandler.class);

   private final LicencaComercialRepository licencaRepository;
   private final EstabelecimentoRepository estabelecimentoRepository;
   private final LicencaComercialMapper licencaMapper;

   public UpdateLicencaComercialCommandHandler(LicencaComercialRepository licencaRepository, EstabelecimentoRepository estabelecimentoRepository, LicencaComercialMapper licencaMapper) {

       this.licencaRepository = licencaRepository;
       this.estabelecimentoRepository = estabelecimentoRepository;
       this.licencaMapper = licencaMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<LicencaResponseDTO> handle(UpdateLicencaComercialCommand command) {
      var dto = command.getLicencarequest();
      var idLicenca = command.getIdLicenca();

      // Buscar licença existente pelo identificador
      LicencaComercial licenca = licencaRepository.findById(Identificador.from(idLicenca))
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Licença Comercial não encontrada: " + idLicenca));

      // Buscar estabelecimento referenciado
      Estabelecimento estabelecimento = estabelecimentoRepository.findById(Identificador.from(dto.getIdEstabelecimento()))
              .orElseThrow(() -> IgrpResponseStatusException.badRequest("Estabelecimento não encontrado: " + dto.getIdEstabelecimento()));

      // Atualizar a licença no domínio
      licenca.atualizar(
              dto.getAlvara(),
              dto.getDataInicioLicenca(),
              dto.getDataFimLicenca(),
              dto.getDataRenovacaoLicenca(),
              dto.getHorarioInicioFuncionamento(),
              dto.getHorarioFimFuncionamento(),
              dto.getDesignacao(),
              dto.getEstadoLicenca(),
              null,
              estabelecimento
      );

      // Salvar licença atualizada
      LicencaComercial updated = licencaRepository.save(licenca);

      // Mapear para DTO e retornar
      return ResponseEntity.ok(licencaMapper.toDTO(updated));
   }

}
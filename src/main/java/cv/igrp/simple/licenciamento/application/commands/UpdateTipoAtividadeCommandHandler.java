package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.models.TipoAtividade;
import cv.igrp.simple.licenciamento.domain.repository.TipoAtividadeRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.TipoAtividadeMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeResponseDTO;

@Component
public class UpdateTipoAtividadeCommandHandler implements CommandHandler<UpdateTipoAtividadeCommand, ResponseEntity<TipoAtividadeResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTipoAtividadeCommandHandler.class);

   private final TipoAtividadeRepository tipoAtividadeRepository;
   private final TipoAtividadeMapper tipoAtividadeMapper;

   public UpdateTipoAtividadeCommandHandler(TipoAtividadeRepository tipoAtividadeRepository, TipoAtividadeMapper tipoAtividadeMapper) {

       this.tipoAtividadeRepository = tipoAtividadeRepository;
       this.tipoAtividadeMapper = tipoAtividadeMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<TipoAtividadeResponseDTO> handle(UpdateTipoAtividadeCommand command) {
      var dto = command.getTipoatividaderequest();
      var idTipoAtividade = Identificador.from(command.getIdTipoAtividade());

      TipoAtividade tipoAtividade = tipoAtividadeRepository.findById(idTipoAtividade)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Tipo de Atividade não encontrado: " + command.getIdTipoAtividade()));

      // Atualiza os dados do domínio
      tipoAtividade.atualizar(dto.getNome(), dto.getCodigo(), dto.getDescricao());

      // Salva no repositório
      TipoAtividade atualizado = tipoAtividadeRepository.save(tipoAtividade);

      // Mapeia para DTO de resposta
      TipoAtividadeResponseDTO responseDTO = tipoAtividadeMapper.toDTO(atualizado);

      return ResponseEntity.ok(responseDTO);
   }

}
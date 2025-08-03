package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.models.TipoAtividade;
import cv.igrp.simple.licenciamento.domain.repository.TipoAtividadeRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.TipoAtividadeMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.TipoAtividadeResponseDTO;

@Component
public class CreateTipoAtividadeCommandHandler implements CommandHandler<CreateTipoAtividadeCommand, ResponseEntity<TipoAtividadeResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateTipoAtividadeCommandHandler.class);

   private final TipoAtividadeRepository tipoAtividadeRepository;
   private final TipoAtividadeMapper tipoAtividadeMapper;

   public CreateTipoAtividadeCommandHandler(TipoAtividadeRepository tipoAtividadeRepository, TipoAtividadeMapper tipoAtividadeMapper) {

       this.tipoAtividadeRepository = tipoAtividadeRepository;
       this.tipoAtividadeMapper = tipoAtividadeMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<TipoAtividadeResponseDTO> handle(CreateTipoAtividadeCommand command) {
      var dto = command.getTipoatividaderequest();

      // Criar o domínio usando os dados do DTO
      var novaTipoAtividade = TipoAtividade.criarNovo(dto.getNome(), dto.getCodigo(), dto.getDescricao());

      var saved = tipoAtividadeRepository.save(novaTipoAtividade);

      // Converter para DTO de resposta
      var responseDTO = tipoAtividadeMapper.toDTO(saved);

      return ResponseEntity.ok(responseDTO);
   }

}
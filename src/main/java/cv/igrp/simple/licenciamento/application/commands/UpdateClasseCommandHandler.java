package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.models.Classe;
import cv.igrp.simple.licenciamento.domain.repository.ClasseRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.ClasseMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;

@Component
public class UpdateClasseCommandHandler implements CommandHandler<UpdateClasseCommand, ResponseEntity<ClasseResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateClasseCommandHandler.class);

   private final ClasseRepository classeRepository;
   private final ClasseMapper classeMapper;
   public UpdateClasseCommandHandler(ClasseRepository classeRepository, ClasseMapper classeMapper) {

       this.classeRepository = classeRepository;
       this.classeMapper = classeMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<ClasseResponseDTO> handle(UpdateClasseCommand command) {
      var dto = command.getClasserequest();
      var classeId = command.getClasseId();

      // Buscar classe existente
      var classe = classeRepository.findById(Identificador.from(classeId))
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Classe não encontrada: " + classeId));

      // Atualizar dados do domínio
      classe.atualizar(dto.getCodigo(), dto.getDescricao());

      // Salvar entidade atualizada
      var updated = classeRepository.save(classe);

      // Mapear para DTO e retornar
      return ResponseEntity.ok(classeMapper.toDTO(updated));
   }

}
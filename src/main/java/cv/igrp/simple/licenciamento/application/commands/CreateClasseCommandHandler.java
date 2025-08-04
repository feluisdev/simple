package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.application.dto.ClasseRequestDTO;
import cv.igrp.simple.licenciamento.domain.models.Classe;
import cv.igrp.simple.licenciamento.domain.repository.ClasseRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.ClasseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.ClasseResponseDTO;

@Component
public class CreateClasseCommandHandler implements CommandHandler<CreateClasseCommand, ResponseEntity<ClasseResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateClasseCommandHandler.class);

   private final ClasseRepository classeRepository;
   private final ClasseMapper classeMapper;

   public CreateClasseCommandHandler(ClasseRepository classeRepository, ClasseMapper classeMapper) {

       this.classeRepository = classeRepository;
       this.classeMapper = classeMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<ClasseResponseDTO> handle(CreateClasseCommand command) {
      var dto = command.getClasserequest();

      var novaClasse = Classe.criarNovo(dto.getCodigo(), dto.getDescricao());


      Classe saved = classeRepository.save(novaClasse);

      return ResponseEntity.ok(classeMapper.toDTO(saved));
   }

}
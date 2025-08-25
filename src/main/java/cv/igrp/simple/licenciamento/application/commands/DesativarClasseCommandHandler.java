package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.ClasseRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class DesativarClasseCommandHandler implements CommandHandler<DesativarClasseCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DesativarClasseCommandHandler.class);

   private final ClasseRepository repository;

   public DesativarClasseCommandHandler(ClasseRepository repository) {

       this.repository = repository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(DesativarClasseCommand command) {
      var id = Identificador.from(command.getIdClasse());

      var classe = repository.findById(id)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Classe não encontrada"));

      classe.desativar(); // Atualiza estado para ATIVO no domínio

      repository.save(classe); // Persiste alteração

      return ResponseEntity.ok(Map.of("message", "Classe desativada com sucesso"));
   }

}
package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.repository.EstabelecimentoRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class AtivarEstabelecimentoCommandHandler implements CommandHandler<AtivarEstabelecimentoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(AtivarEstabelecimentoCommandHandler.class);

   private final EstabelecimentoRepository repository;

   public AtivarEstabelecimentoCommandHandler(EstabelecimentoRepository repository) {

       this.repository = repository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(AtivarEstabelecimentoCommand command) {
      var id = Identificador.from(command.getIdEstabelecimento());

      var estabelecimento = repository.findById(id)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Estabelecimento não encontrado"));

      estabelecimento.ativar(); // Atualiza estado no domínio

      repository.save(estabelecimento); // Persiste

      return ResponseEntity.ok(Map.of("message", "Estabelecimento ativado com sucesso"));
   }

}
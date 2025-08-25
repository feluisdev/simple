package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.licenciamento_comercial.repository.TipoAtividadeRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class AtivarTipoAtividadeCommandHandler implements CommandHandler<AtivarTipoAtividadeCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(AtivarTipoAtividadeCommandHandler.class);

   private final TipoAtividadeRepository repository;

   public AtivarTipoAtividadeCommandHandler(TipoAtividadeRepository repository) {

       this.repository = repository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(AtivarTipoAtividadeCommand command) {
      var id = Identificador.from(command.getIdTipoAtividade());

      var tipoAtividade = repository.findById(id)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("TipoAtividade não encontrado"));

      tipoAtividade.ativar(); // altera o estado para ATIVO no domínio

      repository.save(tipoAtividade); // persiste a mudança

      return ResponseEntity.ok(Map.of("message", "TipoAtividade ativado com sucesso"));
   }

}
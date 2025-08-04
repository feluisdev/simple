package cv.igrp.simple.utente.application.queries;
import cv.igrp.simple.utente.domain.repository.UtenteReadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import cv.igrp.simple.shared.application.dto.ComboIntegerDTO;

@Component
public class GetUtentesAtivosQueryHandler implements QueryHandler<GetUtentesAtivosQuery, ResponseEntity<List<ComboIntegerDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetUtentesAtivosQueryHandler.class);


  private final UtenteReadRepository utenteReadRepository;

  public GetUtentesAtivosQueryHandler(UtenteReadRepository utenteReadRepository) {

      this.utenteReadRepository = utenteReadRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<ComboIntegerDTO>> handle(GetUtentesAtivosQuery query) {
     var ativos = utenteReadRepository.getUtenteAtivos();

     var result = ativos.stream()
             .map(combo -> new ComboIntegerDTO(combo.label(), combo.value()))
             .toList();

     return ResponseEntity.ok(result);
  }

}
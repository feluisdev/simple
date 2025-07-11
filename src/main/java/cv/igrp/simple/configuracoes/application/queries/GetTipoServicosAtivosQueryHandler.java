package cv.igrp.simple.configuracoes.application.queries;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoReadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import cv.igrp.simple.shared.application.dto.ComboStringDTO;

@Component
public class GetTipoServicosAtivosQueryHandler implements QueryHandler<GetTipoServicosAtivosQuery, ResponseEntity<List<ComboStringDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetTipoServicosAtivosQueryHandler.class);

  private final TipoServicoReadRepository tipoServicoReadRepository;

  public GetTipoServicosAtivosQueryHandler(TipoServicoReadRepository tipoServicoReadRepository) {

      this.tipoServicoReadRepository = tipoServicoReadRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<ComboStringDTO>> handle(GetTipoServicosAtivosQuery query) {
     var ativos = tipoServicoReadRepository.getTipoServicoAtivos();

     var result = ativos.stream()
             .map(combo -> new ComboStringDTO(combo.label(), combo.value()))
             .toList();

     return ResponseEntity.ok(result);
  }

}
package cv.igrp.simple.configuracoes.application.queries;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaReadRepository;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.shared.application.dto.ComboStringDTO;

import java.util.List;

@Component
public class GetCategoriaServicoAtivosQueryHandler implements QueryHandler<GetCategoriaServicoAtivosQuery, ResponseEntity<List<ComboStringDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoriaServicoAtivosQueryHandler.class);

  private final CategoriaReadRepository categoriaReadRepository;

  public GetCategoriaServicoAtivosQueryHandler(CategoriaReadRepository categoriaReadRepository) {

      this.categoriaReadRepository = categoriaReadRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<ComboStringDTO>> handle(GetCategoriaServicoAtivosQuery query) {

    var ativos = categoriaReadRepository.getCategoriasAtivas();
     var result = ativos.stream()
             .map(combo -> new ComboStringDTO(combo.label(), combo.value()))
             .toList();

     return ResponseEntity.ok(result);
  }

}
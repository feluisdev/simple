package cv.igrp.simple.configuracoes.application.queries;
import cv.igrp.simple.configuracoes.application.constants.Estado;
import cv.igrp.simple.configuracoes.application.dto.ListaCategoriaDTO;
import cv.igrp.simple.configuracoes.application.dto.ListaTipoServicoDTO;
import cv.igrp.simple.configuracoes.domain.models.TipoServicoFilter;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.WrapperListaTipoServicoDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaTipoServicoQueryHandler implements QueryHandler<ListaTipoServicoQuery, ResponseEntity<WrapperListaTipoServicoDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListaTipoServicoQueryHandler.class);

  private final TipoServicoRepository tipoServicoRepository;

  public ListaTipoServicoQueryHandler(TipoServicoRepository tipoServicoRepository) {

      this.tipoServicoRepository = tipoServicoRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaTipoServicoDTO> handle(ListaTipoServicoQuery query) {
    // TODO: Implement the query handling logic here
     var filter = new TipoServicoFilter();
     filter.setNome(query.getNome());
     filter.setCodigo(query.getCodigo());
     filter.setCategoriaId(query.getCategoriaId());
     filter.setPageSize(Integer.parseInt(query.getTamanho()));
     filter.setPageNumber(Integer.parseInt(query.getPagina()));


     var tipoServicos = tipoServicoRepository.getAll(filter);

     var listaDTO = tipoServicos.stream()
             .map(tipoServico -> {
               var dto = new ListaTipoServicoDTO();
               dto.setId(tipoServico.getId());
               dto.setTipoServicoId(tipoServico.getTipoServicoUuid().getValue().toString());
               dto.setNome(tipoServico.getNome());
               dto.setCodigo(tipoServico.getCodigo());
               dto.setCategoria(tipoServico.getCategoria().getNome());
               dto.setEstado(tipoServico.isEstado()? Estado.ATIVO.getCode() : Estado.INATIVO.getCode());
               return dto;
             })
             .toList();

     var wrapper = new WrapperListaTipoServicoDTO();
     wrapper.setContent(listaDTO);
     wrapper.setPageNumber(Integer.parseInt(query.getPagina()));
     wrapper.setPageSize(Integer.parseInt(query.getTamanho()));
     wrapper.setTotalElements((long) listaDTO.size());

     return ResponseEntity.ok(wrapper);

   }


}
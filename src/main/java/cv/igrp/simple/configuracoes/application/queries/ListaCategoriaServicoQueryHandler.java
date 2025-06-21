package cv.igrp.simple.configuracoes.application.queries;
import cv.igrp.simple.configuracoes.application.constants.Estado;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.dto.ListaCategoriaDTO;
import cv.igrp.simple.configuracoes.domain.models.CategoriaFilter;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.WrapperListaCategoriaServicoDTO;

@Component
public class ListaCategoriaServicoQueryHandler implements QueryHandler<ListaCategoriaServicoQuery, ResponseEntity<WrapperListaCategoriaServicoDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListaCategoriaServicoQueryHandler.class);


  private final CategoriaServicoRepository categoriaServicoRepository;

  public ListaCategoriaServicoQueryHandler(CategoriaServicoRepository categoriaServicoRepository) {

      this.categoriaServicoRepository = categoriaServicoRepository;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaCategoriaServicoDTO> handle(ListaCategoriaServicoQuery query) {

     var filter = new CategoriaFilter();
     filter.setNome(query.getNome());
     filter.setCodigo(query.getCodigo());
     filter.setPageSize(Integer.parseInt(query.getTamanho()));
     filter.setPageNumber(Integer.parseInt(query.getPagina()));

   var categorias = categoriaServicoRepository.getAll(filter);

       var listaDTO = categorias.stream()
               .map(categoria -> {
                   var dto = new ListaCategoriaDTO();
                   dto.setId(categoria.getId());
                   dto.setNome(categoria.getNome());
                   dto.setCodigo(categoria.getCodigo());
                   dto.setEstado(categoria.isEstado()? Estado.ATIVO.getCode() : Estado.INATIVO.getCode()); // campo "estado" no domínio → "ativo" no DTO
                   return dto;
               })
               .toList();


       var wrapper = new WrapperListaCategoriaServicoDTO();
       wrapper.setContent(listaDTO);
       wrapper.setPageNumber(Integer.parseInt(query.getPagina()));
       wrapper.setPageSize(Integer.parseInt(query.getTamanho()));
       wrapper.setTotalElements((long) listaDTO.size());

       return ResponseEntity.ok(wrapper);


   }

}
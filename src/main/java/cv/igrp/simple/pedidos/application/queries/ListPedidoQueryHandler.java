package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.pedidos.application.dto.WrapperListaPedidoDTO;
import cv.igrp.simple.pedidos.domain.filter.PedidoFilter;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ListPedidoQueryHandler implements QueryHandler<ListPedidoQuery, ResponseEntity<WrapperListaPedidoDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListPedidoQueryHandler.class);

  private final PedidoRepository pedidoRepository;

  private final PedidoMapper pedidoMapper;

  public ListPedidoQueryHandler(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {

      this.pedidoRepository = pedidoRepository;
      this.pedidoMapper = pedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListaPedidoDTO> handle(ListPedidoQuery query) {

     var filter = PedidoFilter.builder()
             .codigoAcompanhamento(query.getCodigoAcompanhamento() != null
                     ? CodigoAcompanhamento.of(query.getCodigoAcompanhamento())
                     : null)
             .tipoServicoId(query.getTipoServicoId() != null
                     ? Identificador.from(query.getTipoServicoId())
                     : null)
             .utenteId(query.getUtenteId())
             .dataDe(query.getDataDe() != null ? LocalDate.parse(query.getDataDe()) : null)
             .dataAte(query.getDataAte() != null ? LocalDate.parse(query.getDataAte()) : null)
             .utenteNumero(query.getNumeroUtente())
             .utenteNome(query.getNomeUtente())
             .pageSize(Integer.parseInt(query.getTamanho()))
             .pageNumber(Integer.parseInt(query.getPagina()))
             .build();

     var pedidos = pedidoRepository.findAll(filter);

     var listaDTO = pedidos.stream()
             .map(pedidoMapper::toPedidoResponseDTO)
             .toList();

     var wrapper = new WrapperListaPedidoDTO();
     wrapper.setContent(listaDTO);
     wrapper.setPageNumber(Integer.parseInt(query.getPagina()));  // Ajuste se o seu query for diferente
     wrapper.setPageSize(Integer.parseInt(query.getTamanho()));
     wrapper.setTotalElements((long) listaDTO.size());

     return ResponseEntity.ok(wrapper);

  }

}
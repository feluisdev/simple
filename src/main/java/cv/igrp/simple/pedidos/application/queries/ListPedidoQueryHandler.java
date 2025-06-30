package cv.igrp.simple.pedidos.application.queries;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.configuracoes.domain.models.TipoServicoFilter;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;
import cv.igrp.simple.pedidos.application.dto.WrapperListaPedidoDTO;
import cv.igrp.simple.pedidos.domain.models.PedidoFilter;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.domain.valueobject.CodigoAcompanhamento;
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

  public ListPedidoQueryHandler(PedidoRepository pedidoRepository) {

      this.pedidoRepository = pedidoRepository;
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
             .map(pedido -> {
               var dto = new PedidoResponseDTO();

               dto.setId(pedido.getId());
               dto.setPedidoId(pedido.getPedidoUuid() != null ? pedido.getPedidoUuid().getValor().toString() : null);
               dto.setCodigoAcompanhamento(pedido.getCodigoAcompanhamento() != null ? pedido.getCodigoAcompanhamento().getValor() : null);

               if (pedido.getTipoServico() != null) {
                 dto.setTipoServicoId(pedido.getTipoServico().getId());
                 dto.setTipoServicoNome(pedido.getTipoServico().getNome());
               }

               if (pedido.getUtente() != null) {
                 dto.setUtenteId(pedido.getUtente().getId() != null ? pedido.getUtente().getId().toString() : null);
                 dto.setUtenteNome(pedido.getUtente().getNome());
               }

               dto.setEtapaAtualId(pedido.getEtapaAtualId() != null ? pedido.getEtapaAtualId().toString() : null);
               dto.setEtapaAtualNome(null); // todo, não tem no domínio, ajustar depois

               if (pedido.getStatus() != null) {
                 dto.setStatusId(pedido.getStatus().getId() != null ? pedido.getStatus().getId().toString() : null);
                 dto.setStatusNome(pedido.getStatus().getNome());
                 dto.setStatusCor(pedido.getStatus().getCor());
               }

               dto.setDataInicio(pedido.getDataSolicitacao());
               dto.setDataPrevisao(pedido.getDataPrevisaoConclusao());
               dto.setDataConclusao(null); // todo, não tem no domínio, ajustar depois

               dto.setObservacoes(pedido.getObservacao());
               dto.setValorTotal(pedido.getValorTotal());
               dto.setOrigem(pedido.getOrigem());
               dto.setPrioridade(pedido.getPrioridade() != null ? pedido.getPrioridade().toString() : null);
               dto.setDataCriacao(pedido.getDataSolicitacao());
               return dto;
             })
             .toList();

     var wrapper = new WrapperListaPedidoDTO();
     wrapper.setContent(listaDTO);
     wrapper.setPageNumber(Integer.parseInt(query.getPagina()));  // Ajuste se o seu query for diferente
     wrapper.setPageSize(Integer.parseInt(query.getTamanho()));
     wrapper.setTotalElements((long) listaDTO.size());

     return ResponseEntity.ok(wrapper);

  }

}
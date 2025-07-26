package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.DocumentoPedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.DocumentoPedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

import cv.igrp.simple.pedidos.application.dto.DocumentoPedidoResponseDTO;

@Component
public class ListPedidoDocumentosQueryHandler implements QueryHandler<ListPedidoDocumentosQuery, ResponseEntity<List<DocumentoPedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListPedidoDocumentosQueryHandler.class);


  private final DocumentoPedidoRepository documentoPedidoRepository;
  private final DocumentoPedidoMapper documentoPedidoMapper;

  public ListPedidoDocumentosQueryHandler(DocumentoPedidoRepository documentoPedidoRepository, DocumentoPedidoMapper documentoPedidoMapper) {

      this.documentoPedidoRepository = documentoPedidoRepository;
      this.documentoPedidoMapper = documentoPedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<DocumentoPedidoResponseDTO>> handle(ListPedidoDocumentosQuery query) {
     var pedidoId = Identificador.from(query.getPedidoId());

     var documentos = documentoPedidoRepository.findDocumentosByPedido(pedidoId);

     List<DocumentoPedidoResponseDTO> dtos = documentos.stream()
             .map(documentoPedidoMapper::toResponseDTO)
             .collect(Collectors.toList());

     return ResponseEntity.ok(dtos);
  }

}
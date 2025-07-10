package cv.igrp.simple.pedidos.application.queries;
import cv.igrp.simple.pedidos.domain.repository.DocumentoPedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.DocumentoPedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.pedidos.application.dto.DocumentoPedidoResponseDTO;

@Component
public class GetDocumentoPedidoByIdQueryHandler implements QueryHandler<GetDocumentoPedidoByIdQuery, ResponseEntity<DocumentoPedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetDocumentoPedidoByIdQueryHandler.class);

  private final DocumentoPedidoRepository documentoPedidoRepository;
  private final DocumentoPedidoMapper documentoPedidoMapper;

  public GetDocumentoPedidoByIdQueryHandler(DocumentoPedidoRepository documentoPedidoRepository, DocumentoPedidoMapper documentoPedidoMapper) {

      this.documentoPedidoRepository = documentoPedidoRepository;
      this.documentoPedidoMapper = documentoPedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<DocumentoPedidoResponseDTO> handle(GetDocumentoPedidoByIdQuery query) {
     var documentoId = Identificador.from(query.getDocumentoId());
     var pedidoId = Identificador.from(query.getPedidoId());

        LOGGER.info("documento com ID: {} para o pedido com ID: {}", documentoId, pedidoId);

     var documento = documentoPedidoRepository
             .findByPedidoIdAndDocumentoId(pedidoId, documentoId)
             .orElseThrow(() ->
                     IgrpResponseStatusException.notFound("Documento não encontrado para o pedido informado.")
             );

     var dto = documentoPedidoMapper.toResponseDTO(documento);
     return ResponseEntity.ok(dto);
  }

}
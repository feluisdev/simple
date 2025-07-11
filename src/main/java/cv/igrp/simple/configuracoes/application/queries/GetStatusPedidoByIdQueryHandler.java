package cv.igrp.simple.configuracoes.application.queries;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;

@Component
public class GetStatusPedidoByIdQueryHandler implements QueryHandler<GetStatusPedidoByIdQuery, ResponseEntity<StatusPedidoResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetStatusPedidoByIdQueryHandler.class);

  private final StatusPedidoRepository statusPedidoRepository;

  private final StatusPedidoMapper statusPedidoMapper;

  public GetStatusPedidoByIdQueryHandler(StatusPedidoRepository statusPedidoRepository, StatusPedidoMapper statusPedidoMapper) {

      this.statusPedidoRepository = statusPedidoRepository;
      this.statusPedidoMapper = statusPedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<StatusPedidoResponseDTO> handle(GetStatusPedidoByIdQuery query) {

     var domain = statusPedidoRepository
             .getById(Identificador.from(query.getStatusPedidoId()))
             .orElseThrow(() ->
                     new IllegalArgumentException("Status pedido not found with id"+query.getStatusPedidoId()));

     return ResponseEntity.ok(statusPedidoMapper.toStatusPedidoResponseDTO(domain));


  }

}
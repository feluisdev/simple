package cv.igrp.simple.configuracoes.application.queries;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;

@Component
public class ListStatusPedidoQueryHandler implements QueryHandler<ListStatusPedidoQuery, ResponseEntity<List<StatusPedidoResponseDTO>>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ListStatusPedidoQueryHandler.class);


  private final StatusPedidoRepository repository;
  private final StatusPedidoMapper statusPedidoMapper;

  public ListStatusPedidoQueryHandler(StatusPedidoRepository repository, StatusPedidoMapper statusPedidoMapper) {

      this.repository = repository;
      this.statusPedidoMapper = statusPedidoMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<List<StatusPedidoResponseDTO>> handle(ListStatusPedidoQuery query) {
     var lista = repository.getAll();

     var dtoList = lista.stream()
             .map(statusPedidoMapper::toStatusPedidoResponseDTO)
             .collect(Collectors.toList());

     return ResponseEntity.status(HttpStatus.OK).body(dtoList);
  }

}
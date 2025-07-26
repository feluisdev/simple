package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.domain.repository.DocumentoPedidoRepository;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.DocumentoPedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.DocumentoPedidoResponseDTO;

@Component
public class UpdateDocumentoPedidoCommandHandler implements CommandHandler<UpdateDocumentoPedidoCommand, ResponseEntity<DocumentoPedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDocumentoPedidoCommandHandler.class);


   private final DocumentoPedidoRepository documentoPedidoRepository;
   private final DocumentoPedidoMapper documentoPedidoMapper;

   public UpdateDocumentoPedidoCommandHandler(DocumentoPedidoRepository documentoPedidoRepository, DocumentoPedidoMapper documentoPedidoMapper) {
       this.documentoPedidoRepository = documentoPedidoRepository;
       this.documentoPedidoMapper = documentoPedidoMapper;

   }

   @IgrpCommandHandler
   public ResponseEntity<DocumentoPedidoResponseDTO> handle(UpdateDocumentoPedidoCommand command) {
      var documentoId = Identificador.from(command.getDocumentoId());
      var pedidoId = Identificador.from(command.getPedidoId());

      var documento = documentoPedidoRepository
              .findByPedidoIdAndDocumentoId(pedidoId, documentoId)
              .orElseThrow(() -> IgrpResponseStatusException.notFound("Documento não encontrado para o pedido."));

      var dto = command.getUploaddocumentopedido();
      documento.atualizar(dto.getNome(), dto.getDescricao(), dto.getTipoDocumento(), dto.getUrl());


      var atualizado = documentoPedidoRepository.save(documento);

      var response = documentoPedidoMapper.toResponseDTO(atualizado);

      return ResponseEntity.ok(response);
   }

}
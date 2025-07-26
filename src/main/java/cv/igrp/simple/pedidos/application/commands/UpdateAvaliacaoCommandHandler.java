package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedidos.domain.repository.AvaliacaoRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.AvaliacaoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.AvaliacaoPedidoResponseDTO;

@Component
public class UpdateAvaliacaoCommandHandler implements CommandHandler<UpdateAvaliacaoCommand, ResponseEntity<AvaliacaoPedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateAvaliacaoCommandHandler.class);

   private final AvaliacaoRepository avaliacaoRepository;
   private final AvaliacaoMapper avaliacaoMapper;

   public UpdateAvaliacaoCommandHandler(AvaliacaoRepository avaliacaoRepository, AvaliacaoMapper avaliacaoMapper) {

       this.avaliacaoRepository = avaliacaoRepository;
       this.avaliacaoMapper = avaliacaoMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<AvaliacaoPedidoResponseDTO> handle(UpdateAvaliacaoCommand command) {


      var pedidoId = Identificador.from( command.getPedidoId());
        var avaliacaoId = Identificador.from(command.getAvaliacaoId());

        var dto = command.getCreateavaliacaopedido();

        var avaliacao = avaliacaoRepository.findByPedidoIdAndAvaliacaoID(pedidoId,avaliacaoId)
                .orElseThrow(() -> IgrpResponseStatusException.notFound("Avaliação não encontrada para o ID fornecido."));

      avaliacao.atualizar(dto.getComentario(), dto.getNota());

      var avaliacaoSalva = avaliacaoRepository.save(avaliacao);

      var responseDTO = avaliacaoMapper.toResponseDTO(avaliacaoSalva);

      return ResponseEntity.ok(responseDTO);
   }

}
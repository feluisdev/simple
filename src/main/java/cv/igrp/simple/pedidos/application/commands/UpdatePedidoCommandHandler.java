package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.domain.repository.UtenteRepository;
import cv.igrp.simple.pedidos.infrastructure.mappers.PedidoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;

import java.util.UUID;

@Component
public class UpdatePedidoCommandHandler implements CommandHandler<UpdatePedidoCommand, ResponseEntity<PedidoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePedidoCommandHandler.class);

   private final PedidoRepository pedidoRepository;
   private final PedidoMapper pedidoMapper;

   private final TipoServicoRepository tipoServicoRepository;
   private final UtenteRepository utenteRepository;
   public UpdatePedidoCommandHandler(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper, TipoServicoRepository tipoServicoRepository, UtenteRepository utenteRepository) {

       this.pedidoRepository = pedidoRepository;
       this.pedidoMapper = pedidoMapper;
       this.tipoServicoRepository = tipoServicoRepository;
       this.utenteRepository = utenteRepository;

   }

   @IgrpCommandHandler
   public ResponseEntity<PedidoResponseDTO> handle(UpdatePedidoCommand command) {
      // TODO: Implement the command handling logic here
      var pedidoId = Identificador.from(command.getPedidoId());

      var dto = command.getPedidorequest();
      var tipoServicoId = Identificador.from(dto.getTipoServicoId());

      var tipoServico = tipoServicoRepository.findByUuId(tipoServicoId.getValor())
              .orElseThrow(() -> {
                 LOGGER.warn("Tipo de Serviço com ID {} não encontrado.", tipoServicoId);
                 return IgrpResponseStatusException.notFound("Tipo de Serviço não encontrado com o ID: " + tipoServicoId);
              });

      var utenteId = dto.getUtenteId();
      var utente = utenteRepository.findById(utenteId)
              .orElseThrow(() -> {
                 LOGGER.warn("utente com id {} não encontrado.", utenteId);
                 return IgrpResponseStatusException.notFound("utente com id: " + utenteId);
              });


      var pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() ->  IgrpResponseStatusException.notFound("Pedido not found for ID: " + pedidoId));


        pedido.atualizar(
                tipoServico,
                utente,
                dto.getObservacoes(),
                dto.getValorTotal(),
                dto.getOrigem(),
                dto.getPrioridade()
        );

        pedidoRepository.save(pedido);

        LOGGER.info("Pedido atualizado com sucesso: {}", pedidoId);

        var responseDTO = pedidoMapper.toPedidoResponseDTO(pedido);

        return ResponseEntity.ok(responseDTO);

   }

}
package cv.igrp.simple.pedido.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.pedido.domain.models.EtapaProcesso;
import cv.igrp.simple.pedido.domain.repository.EtapaProcessoRepository;
import cv.igrp.simple.pedido.domain.repository.TipoPedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service
public class CreateEtapaProcessoCommandHandler implements CommandHandler<CreateEtapaProcessoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateEtapaProcessoCommandHandler.class);

   private final EtapaProcessoRepository etapaRepo;
   private final TipoPedidoRepository tipoPedidoRepository;

   public CreateEtapaProcessoCommandHandler(EtapaProcessoRepository etapaRepo, TipoPedidoRepository tipoPedidoRepository) {

       this.etapaRepo = etapaRepo;
       this.tipoPedidoRepository = tipoPedidoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(CreateEtapaProcessoCommand command) {
      // TODO: Implement the command handling logic here

      var dto = command.getEtapaprocessorequest();

      var tipoPedido = tipoPedidoRepository.getById(dto.getTipoPedidoId());

      EtapaProcesso etapaAnterior = null;
      if (dto.getEtapaAnteriorId() != null) {
         etapaAnterior = etapaRepo.getById(dto.getEtapaAnteriorId());
      }

      EtapaProcesso etapaProcesso = new EtapaProcesso(
              null,
              tipoPedido,
              dto.getCodigo(),
              dto.getNome(),
              dto.getDescricao(),
              dto.getOrdem(),
              dto.getTempoEstimado(),
              dto.isRequerDocumento(),
              dto.isRequerPagamento(),
              dto.isRequerAprovacao(),
              etapaAnterior
      );

     var etapaProcessoSaved = etapaRepo.save(etapaProcesso);

      var response = Map.of(
              "mensagem",
              "Etapa processo criado com sucesso"
      );

      return ResponseEntity.status(201).body(response);

   }

}
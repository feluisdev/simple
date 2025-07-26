package cv.igrp.simple.pedidos.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import cv.igrp.simple.pedidos.domain.models.Pedido;
import cv.igrp.simple.pedidos.domain.repository.PedidoRepository;
import cv.igrp.simple.pedidos.domain.repository.UtenteRepository;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class CreatePedidoCommandHandler implements CommandHandler<CreatePedidoCommand, ResponseEntity<Map<String, ?>>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePedidoCommandHandler.class);

    private final PedidoRepository pedidoRepository;
    private final TipoServicoRepository tipoServicoRepository;
    private final StatusPedidoRepository statusPedidoRepository;
    private final UtenteRepository utenteRepository;

    public CreatePedidoCommandHandler(PedidoRepository pedidoRepository, TipoServicoRepository tipoServicoRepository, StatusPedidoRepository statusPedidoRepository, UtenteRepository utenteRepository) {

        this.pedidoRepository = pedidoRepository;
        this.tipoServicoRepository = tipoServicoRepository;
        this.statusPedidoRepository = statusPedidoRepository;
        this.utenteRepository = utenteRepository;
    }

    @IgrpCommandHandler
    public ResponseEntity<Map<String, ?>> handle(CreatePedidoCommand command) {
        var dto = command.getPedidorequest();

        var tipoServicoUuid = dto.getTipoServicoId();

        var tipoServico = tipoServicoRepository.findByUuId(UUID.fromString(tipoServicoUuid))
                .orElseThrow(() -> {
                    LOGGER.warn("Tipo de Serviço com ID {} não encontrado.", tipoServicoUuid);
                    return IgrpResponseStatusException.notFound("Tipo de Serviço não encontrado com o ID: " + tipoServicoUuid);
                });

        var codigoInicial = "NOVO";
        var statusPedido = statusPedidoRepository.getByCodigo(codigoInicial)
                .orElseThrow(() -> {
                    LOGGER.warn("Status de Pedido com codigo {} não encontrado.", codigoInicial);
                    return IgrpResponseStatusException.notFound("Status de Pedido com codigo: " + codigoInicial);
                });

        var utenteId = dto.getUtenteId();
        var utente = utenteRepository.findById(utenteId)
                .orElseThrow(() -> {
                    LOGGER.warn("utente com id {} não encontrado.", utenteId);
                    return IgrpResponseStatusException.notFound("utente com id: " + utenteId);
                });

        var pedido = Pedido
                .criarNovo(statusPedido,tipoServico, utente, dto.getObservacoes(),
                        null, dto.getOrigem(), dto.getPrioridade(),
                        dto.getValorTotal());

        var pedidoSaved = pedidoRepository.save(pedido);

        return ResponseEntity.ok(Map.of("pedidoUuid", pedidoSaved.getPedidoUuid().getStringValor()));

    }

}
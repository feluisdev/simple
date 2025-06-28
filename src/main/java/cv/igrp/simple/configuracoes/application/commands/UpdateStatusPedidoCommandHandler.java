package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.configuracoes.infrastructure.mappers.StatusPedidoMapper;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateStatusPedidoCommandHandler implements CommandHandler<UpdateStatusPedidoCommand, ResponseEntity<StatusPedidoResponseDTO>> {

    private final StatusPedidoRepository repository;

    private final StatusPedidoMapper statusPedidoMapper;

    @Override
    @Transactional
    public ResponseEntity<StatusPedidoResponseDTO> handle(UpdateStatusPedidoCommand command) {
        var dto = command.getCreatestatuspedido();

        var domain = repository
                .getById(Identificador.from(command.getStatusPedidoId()))
                .orElseThrow(() ->
                        new IllegalArgumentException("Status pedido not found with id"+command.getStatusPedidoId()));

        domain.atualizarDetalhes(dto.getNome(), dto.getDescricao(),
                        dto.getCor(), dto.getIcone(), dto.isFim(), domain.isVisivelPortal(),
                dto.isAtivo(), dto.getOrdem() );

        var domainSaved = repository.save(domain);
        return ResponseEntity.ok(statusPedidoMapper.toStatusPedidoResponseDTO(domainSaved));
    }

}
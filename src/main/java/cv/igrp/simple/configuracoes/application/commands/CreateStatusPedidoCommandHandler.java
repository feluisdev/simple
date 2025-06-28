package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.simple.configuracoes.application.dto.CreateStatusPedidoDTO;
import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.configuracoes.domain.repository.StatusPedidoRepository;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.StatusPedidoEntity;
import cv.igrp.framework.core.domain.CommandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreateStatusPedidoCommandHandler implements CommandHandler<CreateStatusPedidoCommand, ResponseEntity<Map<String, ?>>> {


    private final StatusPedidoRepository statusPedidoRepository;

    @Override
    @Transactional
    public ResponseEntity<Map<String, ?>> handle(CreateStatusPedidoCommand command) {

        CreateStatusPedidoDTO dto = command.getCreatestatuspedido();

        var statusExistente = statusPedidoRepository.existByCodigo(dto.getCodigo());

        if (statusExistente){
            throw new IllegalStateException("Já existe um status de pedido com o código " + dto.getCodigo());

        }


        StatusPedido novoStatusPedido = StatusPedido.criarNovo(
                dto.getNome(),
                dto.getCodigo(),
                dto.getDescricao(),
                dto.getCor(),
                dto.getIcone(),
                dto.isFim(),
                dto.isVisivelPortal(),
                dto.getOrdem()
        );

        StatusPedido statusSalvo = statusPedidoRepository.save(novoStatusPedido);

        // Retornar o UUID do status criado
        return ResponseEntity.ok(Map.of("statusPedidoUuid", statusSalvo.getStatusPedidoUuid().getValor().toString()));


    }

}
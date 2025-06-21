package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.StatusPedidoEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.StatusPedidoRepository;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterStatusPedidoQueryHandler implements QueryHandler<ObterStatusPedidoQuery, StatusPedidoResponseDTO> {

    private final StatusPedidoRepository repository;

    @Override
    public StatusPedidoResponseDTO handle(ObterStatusPedidoQuery query) {
        StatusPedidoEntity entity = repository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Status de pedido não encontrado"));

        return mapToDTO(entity);
    }

    private StatusPedidoResponseDTO mapToDTO(StatusPedidoEntity entity) {
        return StatusPedidoResponseDTO.builder()
                .id(entity.getId())
                .codigo(entity.getCodigo())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .cor(entity.getCor())
                .icone(entity.getIcone())
                .ordem(entity.getOrdem())
                .visivelPortal(entity.getVisivelPortal())
                .build();
    }
}
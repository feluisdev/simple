package cv.igrp.simple.configuracoes.application.queries.handlers;

import cv.igrp.simple.configuracoes.application.dto.StatusPedidoResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ListaDeStatusPedidoQuery;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.StatusPedidoEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.StatusPedidoRepository;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaDeStatusPedidoQueryHandler implements QueryHandler<ListaDeStatusPedidoQuery, Page<StatusPedidoResponseDTO>> {

    private final StatusPedidoRepository repository;

    @Override
    public Page<StatusPedidoResponseDTO> handle(ListaDeStatusPedidoQuery query) {
        Specification<StatusPedidoEntity> spec = Specification.where(null);

        if (StringUtils.hasText(query.getCodigo())) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("codigo")),
                            "%" + query.getCodigo().toLowerCase() + "%"
                    ));
        }

        if (StringUtils.hasText(query.getNome())) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("nome")),
                            "%" + query.getNome().toLowerCase() + "%"
                    ));
        }

        if (query.getVisivelPortal() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("visivelPortal"), query.getVisivelPortal()));
        }

        Page<StatusPedidoEntity> page = repository.findAll(spec, query.getPageable());

        List<StatusPedidoResponseDTO> dtos = page.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, query.getPageable(), page.getTotalElements());
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
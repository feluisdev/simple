package cv.igrp.simple.configuracoes.application.queries.handlers;

import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ListaDeCategoriasServicosQuery;
import cv.igrp.simple.configuracoes.domain.models.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
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
public class ListaDeCategoriasServicosQueryHandler implements QueryHandler<ListaDeCategoriasServicosQuery, Page<CategoriasServicosResponseDTO>> {

    private final CategoriasServicosRepository repository;

    @Override
    public Page<CategoriasServicosResponseDTO> handle(ListaDeCategoriasServicosQuery query) {
        Specification<CategoriasServicosEntity> spec = Specification.where(null);

        if (StringUtils.hasText(query.getNome())) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("nome")),
                            "%" + query.getNome().toLowerCase() + "%"
                    ));
        }

        if (query.getAtivo() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("ativo"), query.getAtivo()));
        }

        Page<CategoriasServicosEntity> page = repository.findAll(spec, query.getPageable());

        List<CategoriasServicosResponseDTO> dtos = page.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, query.getPageable(), page.getTotalElements());
    }

    private CategoriasServicosResponseDTO mapToDTO(CategoriasServicosEntity entity) {
        return CategoriasServicosResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .icone(entity.getIcone())
                .cor(entity.getCor())
                .ordem(entity.getOrdem())
                .ativo(entity.getAtivo())
                .build();
    }
}
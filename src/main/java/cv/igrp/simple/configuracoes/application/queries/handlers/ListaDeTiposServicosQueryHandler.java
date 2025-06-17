package cv.igrp.simple.configuracoes.application.queries.handlers;

import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ListaDeTiposServicosQuery;
import cv.igrp.simple.configuracoes.domain.models.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.domain.models.TiposServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
import cv.igrp.simple.configuracoes.infrastructure.persistence.TiposServicosRepository;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaDeTiposServicosQueryHandler implements QueryHandler<ListaDeTiposServicosQuery, Page<TiposServicosResponseDTO>> {

    private final TiposServicosRepository repository;
    private final CategoriasServicosRepository categoriasRepository;

    @Override
    public Page<TiposServicosResponseDTO> handle(ListaDeTiposServicosQuery query) {
        Specification<TiposServicosEntity> spec = Specification.where(null);

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

        if (query.getCategoriaId() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("categoriaId"), query.getCategoriaId()));
        }

        if (query.getDisponivelPortal() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("disponivelPortal"), query.getDisponivelPortal()));
        }

        if (query.getAtivo() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("ativo"), query.getAtivo()));
        }

        Page<TiposServicosEntity> page = repository.findAll(spec, query.getPageable());

        List<TiposServicosResponseDTO> dtos = page.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, query.getPageable(), page.getTotalElements());
    }

    private TiposServicosResponseDTO mapToDTO(TiposServicosEntity entity) {
        TiposServicosResponseDTO dto = TiposServicosResponseDTO.builder()
                .id(entity.getId())
                .categoriaId(entity.getCategoriaId())
                .codigo(entity.getCodigo())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .prazoEstimado(entity.getPrazoEstimado())
                .valorBase(entity.getValorBase())
                .requerVistoria(entity.getRequerVistoria())
                .requerAnaliseTec(entity.getRequerAnaliseTec())
                .requerAprovacao(entity.getRequerAprovacao())
                .disponivelPortal(entity.getDisponivelPortal())
                .ativo(entity.getAtivo())
                .build();

        // Adicionar informações da categoria, se disponível
        Optional<CategoriasServicosEntity> categoriaOpt = categoriasRepository.findById(entity.getCategoriaId());
        if (categoriaOpt.isPresent()) {
            CategoriasServicosEntity categoria = categoriaOpt.get();
            dto.setCategoria(CategoriasServicosResponseDTO.builder()
                    .id(categoria.getId())
                    .nome(categoria.getNome())
                    .descricao(categoria.getDescricao())
                    .icone(categoria.getIcone())
                    .cor(categoria.getCor())
                    .ordem(categoria.getOrdem())
                    .ativo(categoria.getAtivo())
                    .build());
        }

        return dto;
    }
}
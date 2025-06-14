package cv.igrp.simple.configuracoes.application.queries.handlers;

import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ObterCategoriaServicosQuery;
import cv.igrp.simple.configuracoes.domain.models.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterCategoriaServicosQueryHandler implements QueryHandler<ObterCategoriaServicosQuery, CategoriasServicosResponseDTO> {

    private final CategoriasServicosRepository repository;

    @Override
    public CategoriasServicosResponseDTO handle(ObterCategoriaServicosQuery query) {
        CategoriasServicosEntity entity = repository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria de serviço não encontrada com o ID: " + query.getId()));

        return mapToDTO(entity);
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
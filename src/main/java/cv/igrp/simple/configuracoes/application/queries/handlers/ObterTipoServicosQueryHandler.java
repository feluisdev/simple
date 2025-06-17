package cv.igrp.simple.configuracoes.application.queries.handlers;

import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ObterTipoServicosQuery;
import cv.igrp.simple.configuracoes.domain.models.CategoriasServicosEntity;
import cv.igrp.simple.configuracoes.domain.models.TiposServicosEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.CategoriasServicosRepository;
import cv.igrp.simple.configuracoes.infrastructure.persistence.TiposServicosRepository;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObterTipoServicosQueryHandler implements QueryHandler<ObterTipoServicosQuery, TiposServicosResponseDTO> {

    private final TiposServicosRepository repository;
    private final CategoriasServicosRepository categoriasRepository;

    @Override
    public TiposServicosResponseDTO handle(ObterTipoServicosQuery query) {
        TiposServicosEntity entity = repository.findById(query.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de serviço não encontrado com o ID: " + query.getId()));

        return mapToDTO(entity);
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
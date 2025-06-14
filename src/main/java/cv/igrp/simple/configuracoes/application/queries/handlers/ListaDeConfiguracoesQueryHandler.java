package cv.igrp.simple.configuracoes.application.queries.handlers;

import cv.igrp.simple.configuracoes.application.dto.ConfiguracoesResponseDTO;
import cv.igrp.simple.configuracoes.application.queries.queries.ListaDeConfiguracoesQuery;
import cv.igrp.simple.configuracoes.domain.models.ConfiguracoesEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.ConfiguracoesRepository;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaDeConfiguracoesQueryHandler implements QueryHandler<ListaDeConfiguracoesQuery, Page<ConfiguracoesResponseDTO>> {

    private final ConfiguracoesRepository configuracoesRepository;

    @Override
    public Page<ConfiguracoesResponseDTO> handle(ListaDeConfiguracoesQuery query) {
        Specification<ConfiguracoesEntity> spec = Specification.where(null);

        if (query.getChave() != null && !query.getChave().isEmpty()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("chave")), "%" + query.getChave().toLowerCase() + "%"));
        }

        if (query.getGrupo() != null && !query.getGrupo().isEmpty()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("grupo")), "%" + query.getGrupo().toLowerCase() + "%"));
        }

        if (query.getTipo() != null && !query.getTipo().isEmpty()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("tipo")), "%" + query.getTipo().toLowerCase() + "%"));
        }

        if (query.getEstado() != null && !query.getEstado().isEmpty()) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(criteriaBuilder.lower(root.get("estado")), query.getEstado().toLowerCase()));
        }

        Page<ConfiguracoesEntity> configuracoesPage = configuracoesRepository.findAll(spec, query.getPageable());

        List<ConfiguracoesResponseDTO> configuracoesResponseDTOs = configuracoesPage.getContent().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(configuracoesResponseDTOs, query.getPageable(), configuracoesPage.getTotalElements());
    }

    private ConfiguracoesResponseDTO mapToDTO(ConfiguracoesEntity entity) {
        return ConfiguracoesResponseDTO.builder()
                .id(entity.getId())
                .chave(entity.getChave())
                .valor(entity.getValor())
                .descricao(entity.getDescricao())
                .tipo(entity.getTipo())
                .grupo(entity.getGrupo())
                .editavel(entity.getEditavel())
                .estado(entity.getEstado())
                .build();
    }
}
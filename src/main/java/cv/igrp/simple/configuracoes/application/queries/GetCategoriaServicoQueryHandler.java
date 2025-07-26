package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;

import java.util.UUID;

@Component
public class GetCategoriaServicoQueryHandler implements QueryHandler<GetCategoriaServicoQuery, ResponseEntity<CategoriasServicosResponseDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoriaServicoQueryHandler.class);

    private final CategoriaServicoRepository categoriaServicoRepository;

    public GetCategoriaServicoQueryHandler(CategoriaServicoRepository categoriaServicoRepository) {

        this.categoriaServicoRepository = categoriaServicoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<CategoriasServicosResponseDTO> handle(GetCategoriaServicoQuery query) {

        var categoriaId = query.getCategoriaServicoId();

        var categoria = categoriaServicoRepository.findByUuId(UUID.fromString(categoriaId))
                .orElseThrow(() -> {
                    LOGGER.warn("Categoria de serviço com ID {} não encontrada para inativação.", categoriaId);
                    return new IllegalArgumentException("Categoria de Serviço não encontrada com o ID: " + categoriaId);
                });


        return ResponseEntity.ok(toDto(categoria));
    }


    private CategoriasServicosResponseDTO toDto(CategoriaServico categoriaServico) {
        var dto = new CategoriasServicosResponseDTO();
        dto.setId(categoriaServico.getId());
        dto.setNome(categoriaServico.getNome());
        dto.setCodigo(categoriaServico.getCodigo());
        dto.setDescricao(categoriaServico.getDescricao());
        dto.setIcone(categoriaServico.getIcone());
        dto.setCor(categoriaServico.getCor());
        dto.setOrdem(categoriaServico.getOrdem());
        dto.setAtivo(categoriaServico.isEstado());
        // todo O CategoriaUuid não está no DTO, se necessário, adicionar.
        return dto;
    }

}
package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.simple.configuracoes.application.commands.BusinessException;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;

@Component
public class GetCategoriaServicoQueryHandler implements QueryHandler<GetCategoriaServicoQuery, ResponseEntity<CategoriasServicosResponseDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoriaServicoQueryHandler.class);

    private final CategoriaServicoRepository categoriaServicoRepository;

    public GetCategoriaServicoQueryHandler(CategoriaServicoRepository categoriaServicoRepository) {
        this.categoriaServicoRepository = categoriaServicoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<CategoriasServicosResponseDTO> handle(GetCategoriaServicoQuery query) {
        LOGGER.info("Buscando categoria de serviço com ID: {}", query.getCategoriaServicoId());

        return categoriaServicoRepository.findById(query.getCategoriaServicoId())
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    LOGGER.warn("Categoria de serviço com ID {} não encontrada.", query.getCategoriaServicoId());
                    return new BusinessException("Categoria de Serviço não encontrada com o ID: " + query.getCategoriaServicoId());
                });
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
        // O CategoriaUuid não está no DTO, se necessário, adicionar.
        return dto;
    }
}
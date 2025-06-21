package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.simple.configuracoes.application.commands.BusinessException;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;

@Component
public class GetTipoServicoQueryHandler implements QueryHandler<GetTipoServicoQuery, ResponseEntity<TiposServicosResponseDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetTipoServicoQueryHandler.class);

    private final TipoServicoRepository tipoServicoRepository;

    public GetTipoServicoQueryHandler(TipoServicoRepository tipoServicoRepository) {
        this.tipoServicoRepository = tipoServicoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<TiposServicosResponseDTO> handle(GetTipoServicoQuery query) {
        LOGGER.info("Buscando tipo de serviço com ID: {}", query.getTipoServicoId());

        return tipoServicoRepository.findById(query.getTipoServicoId())
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> {
                    LOGGER.warn("Tipo de serviço com ID {} não encontrado.", query.getTipoServicoId());
                    return new BusinessException("Tipo de Serviço não encontrado com o ID: " + query.getTipoServicoId());
                });
    }

    private TiposServicosResponseDTO toDto(TipoServico tipoServico) {
        var dto = TiposServicosResponseDTO.builder()
                .id(tipoServico.getId())
                .codigo(tipoServico.getCodigo())
                .nome(tipoServico.getNome())
                .descricao(tipoServico.getDescricao())
                .prazoEstimado(tipoServico.getPrazoEstimado())
                .valorBase(tipoServico.getValorBase())
                .requerVistoria(tipoServico.isVistoria())
                .requerAnaliseTec(tipoServico.isAnaliseTecnica())
                .requerAprovacao(tipoServico.isAprovacao())
                .disponivelPortal(tipoServico.isPortal())
                .ativo(tipoServico.isEstado())
                // tipoServicoUuid não está no DTO. Adicionar se necessário.
                .build();

        if (tipoServico.getCategoria() != null) {
            dto.setCategoriaId(tipoServico.getCategoria().getId());
            dto.setCategoria(toCategoriaDto(tipoServico.getCategoria()));
        }
        return dto;
    }

    private CategoriasServicosResponseDTO toCategoriaDto(CategoriaServico categoriaServico) {
        var dto = new CategoriasServicosResponseDTO();
        dto.setId(categoriaServico.getId());
        dto.setNome(categoriaServico.getNome());
        dto.setCodigo(categoriaServico.getCodigo());
        dto.setDescricao(categoriaServico.getDescricao());
        dto.setIcone(categoriaServico.getIcone());
        dto.setCor(categoriaServico.getCor());
        dto.setOrdem(categoriaServico.getOrdem());
        dto.setAtivo(categoriaServico.isEstado());
        return dto;
    }
}
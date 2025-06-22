package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;

import java.util.UUID;

@Component
public class GetTipoServicoQueryHandler implements QueryHandler<GetTipoServicoQuery, ResponseEntity<TiposServicosResponseDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetTipoServicoQueryHandler.class);

    private final TipoServicoRepository tipoServicoRepository;

    public GetTipoServicoQueryHandler(TipoServicoRepository tipoServicoRepository) {

        this.tipoServicoRepository = tipoServicoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<TiposServicosResponseDTO> handle(GetTipoServicoQuery query) {
        var tipoServicoId = query.getTipoServicoId();

        var tipoServico = tipoServicoRepository.findByUuId(UUID.fromString(tipoServicoId))
                .orElseThrow(() -> {
                    LOGGER.warn("Tipo de Serviço com ID {} não encontrado para inativação.", tipoServicoId);
                    return new IllegalArgumentException("Tipo de Serviço não encontrado com o ID: " + tipoServicoId);
                });

        return ResponseEntity.ok(toDto(tipoServico));

    }


    private TiposServicosResponseDTO toDto(TipoServico tipoServicoSaved) {

        var dto = new TiposServicosResponseDTO();
        dto.setId(tipoServicoSaved.getId());
        dto.setCategoriaId(tipoServicoSaved.getCategoria().getCategoriaUuid().getValue().toString());
        dto.setNome(tipoServicoSaved.getNome());
        dto.setCodigo(tipoServicoSaved.getCodigo());
        dto.setDescricao(tipoServicoSaved.getDescricao());
        dto.setPrazoEstimado(tipoServicoSaved.getPrazoEstimado());
        dto.setValorBase(tipoServicoSaved.getValorBase());
        dto.setDisponivelPortal(tipoServicoSaved.isPortal());
        dto.setRequerAnaliseTec(tipoServicoSaved.isAnaliseTecnica());
        dto.setRequerAprovacao(tipoServicoSaved.isAprovacao());
        dto.setRequerVistoria(tipoServicoSaved.isVistoria());
        dto.setAtivo(tipoServicoSaved.isEstado());
// todo tipoServicoUuid não está no DTO. Adicionar se necessário.
        return dto;

    }

}
package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import cv.igrp.simple.configuracoes.application.dto.UpdateTiposServicosDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class UpdateTipoServicoCommandHandler implements CommandHandler<UpdateTipoServicoCommand, ResponseEntity<Map<String, ?>>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTipoServicoCommandHandler.class);

    private final TipoServicoRepository tipoServicoRepository;
    private final CategoriaServicoRepository categoriaServicoRepository;

    public UpdateTipoServicoCommandHandler(TipoServicoRepository tipoServicoRepository, CategoriaServicoRepository categoriaServicoRepository) {
        this.tipoServicoRepository = tipoServicoRepository;
        this.categoriaServicoRepository = categoriaServicoRepository;
    }

    @IgrpCommandHandler
    @Transactional
    public ResponseEntity<Map<String, ?>> handle(UpdateTipoServicoCommand command) {
        LOGGER.info("Iniciando atualização de tipo de serviço com o comando: {}", command);
        Integer tipoServicoId = command.getTipoServicoId();
        UpdateTiposServicosDTO dto = command.getUpdateDto();

        TipoServico tipoServico = tipoServicoRepository.findById(tipoServicoId)
                .orElseThrow(() -> {
                    LOGGER.warn("Tipo de Serviço com ID {} não encontrado para atualização.", tipoServicoId);
                    return new BusinessException("Tipo de Serviço não encontrado com o ID: " + tipoServicoId);
                });

        CategoriaServico categoria = null;
        if (dto.getCategoriaId() != null) {
            categoria = categoriaServicoRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> {
                        LOGGER.warn("Categoria com ID {} não encontrada para o TipoServico {}", dto.getCategoriaId(), tipoServico.getCodigo());
                        return new BusinessException("Categoria não encontrada com o ID: " + dto.getCategoriaId());
                    });
        } else {
            // Se categoriaId não for fornecido no DTO, mantém a categoria existente.
            categoria = tipoServico.getCategoria();
        }

        // Se o código no DTO for diferente do existente e não for nulo, lança exceção (código imutável)
        // Ou, se o código pudesse ser alterado, aqui viria a lógica de verificação de duplicidade.
        // Por ora, assumindo que o código não muda, não há checagem de duplicidade de código aqui.
        // Se o nome precisar ser único, uma verificação similar à de CategoriaServico poderia ser adicionada.

        tipoServico.atualizarDados(
                dto.getNome() != null ? dto.getNome() : tipoServico.getNome(),
                dto.getDescricao() != null ? dto.getDescricao() : tipoServico.getDescricao(),
                dto.getPrazoEstimado() != null ? dto.getPrazoEstimado() : tipoServico.getPrazoEstimado(),
                dto.getValorBase() != null ? dto.getValorBase() : tipoServico.getValorBase(),
                dto.getRequerVistoria() != null ? dto.getRequerVistoria() : tipoServico.isVistoria(),
                dto.getRequerAnaliseTec() != null ? dto.getRequerAnaliseTec() : tipoServico.isAnaliseTecnica(),
                dto.getRequerAprovacao() != null ? dto.getRequerAprovacao() : tipoServico.isAprovacao(),
                dto.getDisponivelPortal() != null ? dto.getDisponivelPortal() : tipoServico.isPortal(),
                categoria
        );

        if (dto.getAtivo() != null) {
            if (dto.getAtivo()) {
                tipoServico.ativar();
            } else {
                tipoServico.inativar();
            }
        }

        tipoServicoRepository.save(tipoServico);
        LOGGER.info("Tipo de Serviço com ID {} atualizado com sucesso.", tipoServicoId);

        var response = Map.of(
                "id", tipoServico.getId(),
                "tipoServicoUuid", tipoServico.getTipoServicoUuid().toString(),
                "message", "Tipo de Serviço atualizado com sucesso."
        );

        return ResponseEntity.ok(response);
    }
}
package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class InativarTipoServicoCommandHandler implements CommandHandler<InativarTipoServicoCommand, ResponseEntity<Map<String, ?>>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InativarTipoServicoCommandHandler.class);

    private final TipoServicoRepository tipoServicoRepository;

    public InativarTipoServicoCommandHandler(TipoServicoRepository tipoServicoRepository) {
        this.tipoServicoRepository = tipoServicoRepository;
    }

    @IgrpCommandHandler
    @Transactional
    public ResponseEntity<Map<String, ?>> handle(InativarTipoServicoCommand command) {
        LOGGER.info("Iniciando inativação de tipo de serviço com o comando: {}", command);
        Integer tipoServicoId = command.getTipoServicoId();

        TipoServico tipoServico = tipoServicoRepository.findById(tipoServicoId)
                .orElseThrow(() -> {
                    LOGGER.warn("Tipo de Serviço com ID {} não encontrado para inativação.", tipoServicoId);
                    return new BusinessException("Tipo de Serviço não encontrado com o ID: " + tipoServicoId);
                });

        if (!tipoServico.isEstado()) {
            LOGGER.info("Tipo de Serviço com ID {} já está inativo.", tipoServicoId);
            // Pode retornar um status diferente ou a mesma mensagem, dependendo do requisito
            // throw new BusinessException("Tipo de Serviço com ID " + tipoServicoId + " já está inativo.");
        }

        tipoServico.inativar();
        tipoServicoRepository.save(tipoServico);
        LOGGER.info("Tipo de Serviço com ID {} inativado com sucesso.", tipoServicoId);

        var response = Map.of(
                "id", tipoServico.getId(),
                "tipoServicoUuid", tipoServico.getTipoServicoUuid().toString(),
                "message", "Tipo de Serviço inativado com sucesso."
        );

        return ResponseEntity.ok(response);
    }
}
package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import cv.igrp.simple.configuracoes.application.dto.UpdateCategoriasServicosDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class UpdateCategoriaServicoCommandHandler implements CommandHandler<UpdateCategoriaServicoCommand, ResponseEntity<Map<String, ?>>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoriaServicoCommandHandler.class);

    private final CategoriaServicoRepository categoriaServicoRepository;

    public UpdateCategoriaServicoCommandHandler(CategoriaServicoRepository categoriaServicoRepository) {
        this.categoriaServicoRepository = categoriaServicoRepository;
    }

    @IgrpCommandHandler
    @Transactional
    public ResponseEntity<Map<String, ?>> handle(UpdateCategoriaServicoCommand command) {
        LOGGER.info("Iniciando atualização de categoria de serviço com o comando: {}", command);
        Integer categoriaId = command.getCategoriaServicoId();
        UpdateCategoriasServicosDTO dto = command.getUpdateDto();

        CategoriaServico categoria = categoriaServicoRepository.findById(categoriaId)
                .orElseThrow(() -> {
                    LOGGER.warn("Categoria de serviço com ID {} não encontrada para atualização.", categoriaId);
                    return new BusinessException("Categoria de Serviço não encontrada com o ID: " + categoriaId);
                });

        // Opcional: Verificar se o novo nome já existe em outra categoria (se nome deve ser único)
        // categoriaServicoRepository.findByNome(dto.getNome()).ifPresent(existente -> {
        // if (!existente.getId().equals(categoriaId)) {
        // LOGGER.warn("Tentativa de atualizar categoria para nome já existente: {}", dto.getNome());
        // throw new BusinessException("Já existe uma categoria de serviço com o nome: " + dto.getNome());
        // }
        // });

        categoria.atualizarDados(
                dto.getNome(),
                dto.getDescricao(),
                dto.getIcone(),
                dto.getCor(),
                dto.getOrdem()
        );

        if (dto.getAtivo() != null) {
            if (dto.getAtivo()) {
                categoria.ativar();
            } else {
                categoria.inativar();
            }
        }

        categoriaServicoRepository.save(categoria);
        LOGGER.info("Categoria de serviço com ID {} atualizada com sucesso.", categoriaId);

        var response = Map.of(
                "id", categoria.getId(),
                "categoriaUuid", categoria.getCategoriaUuid().toString(),
                "message", "Categoria de Serviço atualizada com sucesso."
        );

        return ResponseEntity.ok(response);
    }
}
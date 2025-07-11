package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateCategoriaServicoCommandHandler implements CommandHandler<UpdateCategoriaServicoCommand, ResponseEntity<CategoriasServicosResponseDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoriaServicoCommandHandler.class);

    private final CategoriaServicoRepository categoriaServicoRepository;

    public UpdateCategoriaServicoCommandHandler(CategoriaServicoRepository categoriaServicoRepository) {

        this.categoriaServicoRepository = categoriaServicoRepository;
    }

    @IgrpCommandHandler
    public ResponseEntity<CategoriasServicosResponseDTO> handle(UpdateCategoriaServicoCommand command) {
        var categoriaId = command.getCategoriaServicoId();

        var dto = command.getCriarcategoriasservicos();

        var categoria = categoriaServicoRepository.findByUuId(UUID.fromString(categoriaId))
                .orElseThrow(() -> {
                    LOGGER.warn("Categoria de serviço com ID {} não encontrada para inativação.", categoriaId);
                    return new IllegalArgumentException("Categoria de Serviço não encontrada com o ID: " + categoriaId);
                });


        categoria.atualizarDados(
                dto.getNome(),
                dto.getDescricao(),
                dto.getIcone(),
                dto.getCor(),
                dto.getOrdem()
        );

        if (dto.isAtivo()) {
            categoria.ativar();
        } else {
            categoria.inativar();
        }


        var categoriaSaved = categoriaServicoRepository.save(categoria);


        return ResponseEntity.ok(toDto(categoriaSaved));

    }

    private CategoriasServicosResponseDTO toDto(CategoriaServico categoriaServico){
        var dto = new CategoriasServicosResponseDTO();
        dto.setId(categoriaServico.getId());
        dto.setNome(categoriaServico.getNome());
        dto.setCodigo(categoriaServico.getCodigo());
        dto.setDescricao(categoriaServico.getDescricao());
        dto.setOrdem(categoriaServico.getOrdem());
        dto.setAtivo(categoriaServico.isEstado());
        dto.setIcone(categoriaServico.getIcone());
        dto.setCor(categoriaServico.getCor());

        return dto;
    }

}
package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;

import java.util.Map;

@Component
public class CreateCategoriaCommandHandler implements CommandHandler<CreateCategoriaCommand,ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateCategoriaCommandHandler.class);

   private final CategoriaServicoRepository categoriaServicoRepository;

   public CreateCategoriaCommandHandler(CategoriaServicoRepository categoriaServicoRepository) {

       this.categoriaServicoRepository = categoriaServicoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(CreateCategoriaCommand command) {

      LOGGER.info("Iniciando criação de nova categoria de serviço com o comando: {}", command);
      var dto = command.getCriarcategoriasservicos();

      categoriaServicoRepository.findByCodigo(dto.getCodigo()).ifPresent(existingCategoria -> {
         LOGGER.warn("Tentativa de criar categoria com código já existente: {}", dto.getCodigo());
         throw new IllegalArgumentException("Já existe uma categoria de serviço com o código: " + dto.getCodigo());
      });

      var categoria =  CategoriaServico.criar(
              dto.getNome(),
              dto.getDescricao(),
              dto.getIcone(),
              dto.getCor(),
              dto.getCodigo()
      );

      var categoriaSaved = categoriaServicoRepository.save(categoria);
      LOGGER.info("Categoria de serviço criada com sucesso: {}", categoriaSaved.getId());

      var response = Map.of(
              "id", categoriaSaved.getId(),
              "categoriaUuid", categoriaSaved.getCategoriaUuid().getValor()
      );

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(response);

   }

}
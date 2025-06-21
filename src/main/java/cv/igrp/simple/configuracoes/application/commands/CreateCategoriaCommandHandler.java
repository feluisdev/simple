package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class CreateCategoriaCommandHandler implements CommandHandler<CreateCategoriaCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateCategoriaCommandHandler.class);

   private final CategoriaServicoRepository categoriaServicoRepository;

   public CreateCategoriaCommandHandler(CategoriaServicoRepository categoriaServicoRepository) {
       this.categoriaServicoRepository = categoriaServicoRepository;
   }

   @IgrpCommandHandler
   @Transactional
   public ResponseEntity<Map<String, ?>> handle(CreateCategoriaCommand command) {
      LOGGER.info("Iniciando criação de nova categoria de serviço com o comando: {}", command);
      var dto = command.getCriarcategoriasservicos();

      // Verificar se já existe uma categoria com o mesmo código
      categoriaServicoRepository.findByCodigo(dto.getCodigo()).ifPresent(existingCategoria -> {
         LOGGER.warn("Tentativa de criar categoria com código já existente: {}", dto.getCodigo());
         throw new BusinessException("Já existe uma categoria de serviço com o código: " + dto.getCodigo());
      });

      var categoria = CategoriaServico.criar(
              dto.getNome(),
              dto.getDescricao(),
              dto.getIcone(),
              dto.getCor(),
              dto.getCodigo()
              // Ordem e estado são definidos por padrão no método criar da entidade
      );

      var categoriaSaved = categoriaServicoRepository.save(categoria);
      LOGGER.info("Categoria de serviço criada com sucesso: {}", categoriaSaved.getId());

      var response = Map.of(
              "id", categoriaSaved.getId(),
              "categoriaUuid", categoriaSaved.getCategoriaUuid().toString()
      );

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(response);
   }
}
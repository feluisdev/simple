package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;

@Component
public class InativarCategoriaServicoCommandHandler implements CommandHandler<InativarCategoriaServicoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(InativarCategoriaServicoCommandHandler.class);

   private final CategoriaServicoRepository categoriaServicoRepository;

   public InativarCategoriaServicoCommandHandler(CategoriaServicoRepository categoriaServicoRepository) {

       this.categoriaServicoRepository = categoriaServicoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(InativarCategoriaServicoCommand command) {
      LOGGER.info("Iniciando inativação de categoria de serviço com o comando: {}", command);
      String categoriaId = command.getCategoriaServicoId();

      CategoriaServico categoria = categoriaServicoRepository.findWithTiposByCategoriaUuid(UUID.fromString(categoriaId))
              .orElseThrow(() -> {
                 LOGGER.warn("Categoria de serviço com ID {} não encontrada para inativação.", categoriaId);
                 return new IllegalArgumentException("Categoria de Serviço não encontrada com o ID: " + categoriaId);
              });

      // Adicionar verificação se a categoria já está inativa, se necessário
      if (!categoria.isEstado()) {
         LOGGER.info("Categoria de serviço com ID {} já está inativa.", categoriaId);
         // Pode retornar um status diferente ou a mesma mensagem, dependendo do requisito
         // throw new BusinessException("Categoria de Serviço com ID " + categoriaId + " já está inativa.");
      }

      categoria.inativar();
      categoriaServicoRepository.save(categoria);

      var response = Map.of(
              "id", categoria.getId(),
              "categoriaUuid", categoria.getCategoriaUuid().toString(),
              "message", "Categoria de Serviço inativada com sucesso."
      );

      return ResponseEntity.ok(response);

   }

}
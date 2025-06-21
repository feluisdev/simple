package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
public class CreateTipoServicoCommandHandler implements CommandHandler<CreateTipoServicoCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateTipoServicoCommandHandler.class);

   private final TipoServicoRepository tipoServicoRepository;
   private final CategoriaServicoRepository categoriaServicoRepository;

   public CreateTipoServicoCommandHandler(TipoServicoRepository tipoServicoRepository, CategoriaServicoRepository categoriaServicoRepository) {
       this.tipoServicoRepository = tipoServicoRepository;
       this.categoriaServicoRepository = categoriaServicoRepository;
   }

   @IgrpCommandHandler
   @Transactional
   public ResponseEntity<Map<String, ?>> handle(CreateTipoServicoCommand command) {
      LOGGER.info("Iniciando criação de novo tipo de serviço com o comando: {}", command);
      var dto = command.getCriartiposservicos();

      // Verificar se já existe um TipoServico com o mesmo código
      tipoServicoRepository.findByCodigo(dto.getCodigo()).ifPresent(existingTipoServico -> {
         LOGGER.warn("Tentativa de criar TipoServico com código já existente: {}", dto.getCodigo());
         throw new BusinessException("Já existe um tipo de serviço com o código: " + dto.getCodigo());
      });

      // Buscar a CategoriaServico
      CategoriaServico categoria = categoriaServicoRepository.findById(dto.getCategoriaId())
              .orElseThrow(() -> {
                 LOGGER.error("Categoria com ID {} não encontrada para o TipoServico {}", dto.getCategoriaId(), dto.getCodigo());
                 return new BusinessException("Categoria não encontrada com o ID: " + dto.getCategoriaId());
              });

      TipoServico tipoServico = TipoServico.criar(
              dto.getCodigo(),
              dto.getNome(),
              dto.getDescricao(),
              dto.getPrazoEstimado(),
              dto.getValorBase(),
              dto.isRequerVistoria(),
              dto.isRequerAnaliseTec(),
              dto.isRequerAprovacao(),
              dto.isDisponivelPortal(),
              categoria
      );

      var tipoServicoSaved = tipoServicoRepository.save(tipoServico);
      LOGGER.info("Tipo de serviço criado com sucesso: {}", tipoServicoSaved.getId());

      var response = Map.of(
              "id", tipoServicoSaved.getId(),
              "tipoServicoUuid", tipoServicoSaved.getTipoServicoUuid().toString()
      );

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(response);
   }
}
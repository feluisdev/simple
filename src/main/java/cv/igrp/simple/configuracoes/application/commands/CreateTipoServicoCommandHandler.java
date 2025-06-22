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

import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;

import java.util.Map;
import java.util.UUID;

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
   public ResponseEntity<Map<String, ?>> handle(CreateTipoServicoCommand command) {
      LOGGER.info("Iniciando criação de novo tipo de serviço com o comando: {}", command);
      var dto = command.getCriartiposservicos();

      tipoServicoRepository.findByCodigo(dto.getCodigo()).ifPresent(existingTipoServico -> {
         LOGGER.warn("Tentativa de criar TipoServico com código já existente: {}", dto.getCodigo());
         throw new IllegalArgumentException("Já existe um tipo de serviço com o código: " + dto.getCodigo());
      });

      CategoriaServico categoria = categoriaServicoRepository.findByUuId(UUID.fromString(dto.getCategoriaId()))
              .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

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


      var response = Map.of(
              "id", tipoServicoSaved.getId(),
              "tipoServicoUuid", tipoServicoSaved.getTipoServicoUuid().getValue().toString());

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(response);


   }

}
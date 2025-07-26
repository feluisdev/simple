package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.configuracoes.application.dto.TiposServicosResponseDTO;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.configuracoes.application.dto.CriarTiposServicosDTO;

import java.util.UUID;

@Component
public class UpdateTipoServicoCommandHandler implements CommandHandler<UpdateTipoServicoCommand, ResponseEntity<TiposServicosResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTipoServicoCommandHandler.class);

   private final TipoServicoRepository tipoServicoRepository;
   private final CategoriaServicoRepository categoriaServicoRepository;

   public UpdateTipoServicoCommandHandler(TipoServicoRepository tipoServicoRepository, CategoriaServicoRepository categoriaServicoRepository) {

       this.tipoServicoRepository = tipoServicoRepository;
       this.categoriaServicoRepository = categoriaServicoRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<TiposServicosResponseDTO> handle(UpdateTipoServicoCommand command) {
      var tipoServicoId = command.getTipoServicoId();
      var dto = command.getCriartiposservicos();

      TipoServico tipoServico = tipoServicoRepository.findByUuId(UUID.fromString(tipoServicoId))
              .orElseThrow(() -> {
                 LOGGER.warn("Tipo de Serviço com ID {} não encontrado para inativação.", tipoServicoId);
                 return new IllegalArgumentException("Tipo de Serviço não encontrado com o ID: " + tipoServicoId);
              });

      var categoria = categoriaServicoRepository.findByUuId(UUID.fromString(dto.getCategoriaId()))
              .orElseThrow(() -> {
                 LOGGER.warn("Categoria com ID {} não encontrada para o TipoServico {}", dto.getCategoriaId(), tipoServico.getCodigo());
                 return new IllegalArgumentException("Categoria não encontrada com o ID: " + dto.getCategoriaId());
              });

      tipoServico.atualizarDados(
              dto.getNome() != null ? dto.getNome() : tipoServico.getNome(),
              dto.getDescricao() != null ? dto.getDescricao() : tipoServico.getDescricao(),
              dto.getPrazoEstimado() != null ? dto.getPrazoEstimado() : tipoServico.getPrazoEstimado(),
              dto.getValorBase() != null ? dto.getValorBase() : tipoServico.getValorBase(),
              dto.isRequerVistoria(),
              dto.isRequerAnaliseTec(),
              dto.isRequerAprovacao(),
              dto.isDisponivelPortal(),
              categoria
      );

      if(dto.isAtivo())
         tipoServico.ativar();
      else
         tipoServico.inativar();

      var tipoServicoSaved = tipoServicoRepository.save(tipoServico);

      return ResponseEntity.ok(toDto(tipoServicoSaved));
   }

   private TiposServicosResponseDTO toDto(TipoServico tipoServicoSaved) {

      var dto = new TiposServicosResponseDTO();
      dto.setId(tipoServicoSaved.getId());
      dto.setTipoServicoId(tipoServicoSaved.getTipoServicoUuid().getValor().toString());
      dto.setCategoriaId(tipoServicoSaved.getCategoria().getCategoriaUuid().getValor().toString());
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

      return dto;

   }

}
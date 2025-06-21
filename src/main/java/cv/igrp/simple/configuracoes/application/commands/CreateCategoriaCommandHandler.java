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

      var dto = command.getCriarcategoriasservicos();

      var categoria =  CategoriaServico.criar(
              dto.getNome(),
              dto.getDescricao(),
              dto.getIcone(),
              dto.getCor(),
              dto.getCodigo()
      );

      var categoriaSaved = categoriaServicoRepository.save(categoria);


      var response = Map.of(
              "id", categoriaSaved.getId()

      );

      return ResponseEntity
              .status(HttpStatus.CREATED)
              .body(response);

    /* return ResponseEntity.status(HttpStatus.CREATED)
             .body(toDto(categoriaSaved));*/


   }

   private CategoriasServicosResponseDTO toDto(CategoriaServico categoriaServico){

      var dto = new CategoriasServicosResponseDTO();
      dto.setId(categoriaServico.getId());
      dto.setNome(categoriaServico.getNome());
      dto.setDescricao(categoriaServico.getDescricao());
      dto.setOrdem(categoriaServico.getOrdem());
      dto.setAtivo(categoriaServico.isEstado());
      dto.setIcone(categoriaServico.getIcone());
      dto.setCor(categoriaServico.getCor());

      return dto;

   }

}
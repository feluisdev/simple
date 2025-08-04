package cv.igrp.simple.licenciamento.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.simple.licenciamento.domain.models.Classe;
import cv.igrp.simple.licenciamento.domain.models.Estabelecimento;
import cv.igrp.simple.licenciamento.domain.repository.ClasseRepository;
import cv.igrp.simple.licenciamento.domain.repository.EstabelecimentoRepository;
import cv.igrp.simple.licenciamento.domain.repository.TipoAtividadeRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.EstabelecimentoMapper;
import cv.igrp.simple.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CreateEstabelecimentoCommandHandler implements CommandHandler<CreateEstabelecimentoCommand, ResponseEntity<EstabelecimentoResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateEstabelecimentoCommandHandler.class);

   private final EstabelecimentoRepository estabelecimentoRepository;
   private final TipoAtividadeRepository tipoAtividadeRepository;
   private final ClasseRepository classeRepository;
   private final EstabelecimentoMapper estabelecimentoMapper;

   public CreateEstabelecimentoCommandHandler(EstabelecimentoRepository estabelecimentoRepository, TipoAtividadeRepository tipoAtividadeRepository, ClasseRepository classeRepository, EstabelecimentoMapper estabelecimentoMapper) {

       this.estabelecimentoRepository = estabelecimentoRepository;
       this.tipoAtividadeRepository = tipoAtividadeRepository;
       this.classeRepository = classeRepository;
       this.estabelecimentoMapper = estabelecimentoMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<EstabelecimentoResponseDTO> handle(CreateEstabelecimentoCommand command) {
      var dto = command.getEstabelecimentorequest();

      var tipoAtividade = tipoAtividadeRepository
              .findById(Identificador.from(dto.getTipoAtividadeId()))
              .orElseThrow(() -> IgrpResponseStatusException.badRequest("Tipo de Atividade não encontrado: " + dto.getTipoAtividadeId()));


      Set<Classe> classes = new HashSet<>();
      List<String> classesId = dto.getClassesId();
      if (classesId != null && !classesId.isEmpty()) {
         for (String classeId : classesId) {
            Classe classe = classeRepository.findById(Identificador.from(classeId))
                    .orElseThrow(() -> IgrpResponseStatusException.badRequest("Classe não encontrada: " + classeId));
            classes.add(classe);
         }

      }

      // Criar domínio novo (sem classes inicialmente)
      var estabelecimento = Estabelecimento.criarNovo(
              dto.getNome(),
              dto.getGerente(),
              dto.getDescricao(),
              dto.isFlagVistoria(),
              dto.isLicRetalho(),
              dto.getEndereco(),
              dto.getTelefone(),
              dto.getEmail(),
              dto.getNif(),
              tipoAtividade
      );

      // Adicionar classes uma a uma no domínio
      classes.forEach(estabelecimento::adicionarClasse);

      // Salvar no repositório
      var saved = estabelecimentoRepository.save(estabelecimento);

      // Mapear para DTO e retornar
      return ResponseEntity.ok(estabelecimentoMapper.toDTO(saved));
   }

}
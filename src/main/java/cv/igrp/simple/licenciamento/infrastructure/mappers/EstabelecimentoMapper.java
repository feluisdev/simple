package cv.igrp.simple.licenciamento.infrastructure.mappers;

import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseDTO;
import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseLigthDTO;
import cv.igrp.simple.licenciamento.domain.models.Classe;
import cv.igrp.simple.licenciamento.domain.models.Estabelecimento;
import cv.igrp.simple.licenciamento.domain.models.TipoAtividade;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.entity.ClasseEntity;
import cv.igrp.simple.shared.infrastructure.persistence.entity.EstabelecimentoEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EstabelecimentoMapper {

    private final TipoAtividadeMapper tipoAtividadeMapper;
    private final ClasseMapper classeMapper;

    public EstabelecimentoMapper(TipoAtividadeMapper tipoAtividadeMapper, ClasseMapper classeMapper) {
        this.tipoAtividadeMapper = tipoAtividadeMapper;
        this.classeMapper = classeMapper;
    }
    public Estabelecimento toDomain(EstabelecimentoEntity entity) {
        if (entity == null) return null;

        TipoAtividade tipoAtividadeDomain = tipoAtividadeMapper.toDomain(entity.getIdTipoAtividade());

        Set<Classe> classesDomain = Collections.emptySet();
        if (entity.getClasses() != null) {
            classesDomain = entity.getClasses().stream()
                    .map(classeMapper::toDomain)
                    .collect(Collectors.toSet());
        }

        return Estabelecimento.reconstruir(
                entity.getId(),
                Identificador.from(entity.getExternalId()),
                entity.getNome(),
                entity.getGerente(),
                entity.getDescricao(),
                entity.isFlagVistoria(),
                entity.isLicRetalho(),
                entity.getEndereco(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getNif(),
                entity.getEstado(),
                tipoAtividadeDomain,
                classesDomain
        );
    }

    public EstabelecimentoEntity toEntity(Estabelecimento estabelecimento) {
        if (estabelecimento == null) return null;

        var entity = new EstabelecimentoEntity();

        if (estabelecimento.getId() != null) {
            entity.setId(estabelecimento.getId());
        }

        entity.setExternalId(estabelecimento.getIdEstabelecimento().getValor());
        entity.setNome(estabelecimento.getNome());
        entity.setGerente(estabelecimento.getGerente());
        entity.setDescricao(estabelecimento.getDescricao());
        entity.setFlagVistoria(estabelecimento.isFlagVistoria());
        entity.setLicRetalho(estabelecimento.isLicRetalho());
        entity.setEndereco(estabelecimento.getEndereco());
        entity.setTelefone(estabelecimento.getTelefone());
        entity.setEmail(estabelecimento.getEmail());
        entity.setNif(estabelecimento.getNif());
        entity.setEstado(estabelecimento.getEstado());

        // Mapear TipoAtividade para entidade
        entity.setIdTipoAtividade(tipoAtividadeMapper.toEntity(estabelecimento.getTipoAtividade()));

        // Mapear classes para entidade (Set<ClasseEntity>)
        if (estabelecimento.getClasses() != null) {
            Set<ClasseEntity> classesEntity = estabelecimento.getClasses().stream()
                    .map(classeMapper::toEntity)
                    .collect(Collectors.toSet());
            entity.setClasses(classesEntity);
        } else {
            entity.setClasses(Collections.emptySet());
        }

        return entity;
    }


    public EstabelecimentoResponseDTO toDTO(Estabelecimento estabelecimento) {
        if (estabelecimento == null) return null;

        EstabelecimentoResponseDTO dto = new EstabelecimentoResponseDTO();

        dto.setEstabelecimentoId(estabelecimento.getIdEstabelecimento().getStringValor());
        dto.setNome(estabelecimento.getNome() != null ? estabelecimento.getNome() : "");
        dto.setGerente(estabelecimento.getGerente());
        dto.setDescricao(estabelecimento.getDescricao());
        dto.setEndereco(estabelecimento.getEndereco());
        dto.setTelefone(estabelecimento.getTelefone());
        dto.setEmail(estabelecimento.getEmail());
        dto.setNif(estabelecimento.getNif());
        dto.setFlagVistoria(estabelecimento.isFlagVistoria());
        dto.setLicRetalho(estabelecimento.isLicRetalho());

        dto.setTipoAtividade(
                estabelecimento.getTipoAtividade() != null
                        ? tipoAtividadeMapper.toDTO(estabelecimento.getTipoAtividade()) : null);

        // Lista de IDs das classes
        if (estabelecimento.getClasses() != null) {
            dto.setClasses(
                    estabelecimento.getClasses().stream()
                            .map(classeMapper::toDTO)
                            .collect(Collectors.toList())
            );
        }

        dto.setEstado(estabelecimento.getEstado() != null ? estabelecimento.getEstado().name() : "");
        dto.setEstadoDesc(estabelecimento.getEstado() != null ? estabelecimento.getEstado().getDescription() : "");

        return dto;
    }


    public EstabelecimentoResponseLigthDTO toLigthDTO(Estabelecimento estabelecimento) {
        if (estabelecimento == null) return null;

        var dto = new EstabelecimentoResponseLigthDTO();

        dto.setEstabelecimentoId(estabelecimento.getIdEstabelecimento().getStringValor());
        dto.setNome(estabelecimento.getNome() != null ? estabelecimento.getNome() : "");
        dto.setGerente(estabelecimento.getGerente());
        dto.setDescricao(estabelecimento.getDescricao());
        dto.setEndereco(estabelecimento.getEndereco());
        dto.setTelefone(estabelecimento.getTelefone());
        dto.setEmail(estabelecimento.getEmail());
        dto.setNif(estabelecimento.getNif());
        dto.setFlagVistoria(estabelecimento.isFlagVistoria());
        dto.setLicRetalho(estabelecimento.isLicRetalho());

        dto.setTipoAtividadeId(estabelecimento.getTipoAtividade()!=null ? estabelecimento.getTipoAtividade().getIdTipoAtividade().getStringValor() : null);
        dto.setTipoAtividadeNome(estabelecimento.getTipoAtividade()!=null ? estabelecimento.getTipoAtividade().getNome() : null);

        // Lista de IDs das classes
        if (estabelecimento.getClasses() != null) {
            dto.setClasses(
                    estabelecimento.getClasses().stream()
                            .map(classe -> classe.getIdClasse().getStringValor())
                            .collect(Collectors.toList())
            );
        }

        dto.setEstado(estabelecimento.getEstado() != null ? estabelecimento.getEstado().name() : "");
        dto.setEstadoDesc(estabelecimento.getEstado() != null ? estabelecimento.getEstado().getDescription() : "");

        return dto;
    }
}

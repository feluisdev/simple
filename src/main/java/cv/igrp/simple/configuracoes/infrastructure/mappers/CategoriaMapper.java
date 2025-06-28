package cv.igrp.simple.configuracoes.infrastructure.mappers;

import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriaServicoEntity;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoriaMapper {

    private final TipoServicoMapper tipoServicoMapper;

    public CategoriaMapper(TipoServicoMapper tipoServicoMapper) {
        this.tipoServicoMapper = tipoServicoMapper;
    }

    public CategoriaServicoEntity toEntity(CategoriaServico domain) {
        if (domain == null) return null;

        CategoriaServicoEntity entity = new CategoriaServicoEntity();

        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setDescricao(domain.getDescricao());
        entity.setIcon(domain.getIcone());
        entity.setCor(domain.getCor());
        entity.setOrdem(domain.getOrdem());
        entity.setEstado(domain.isEstado());
        entity.setCodigo(domain.getCodigo());
        entity.setCategoriaUuid(domain.getCategoriaUuid().getValor());

        if (domain.getTiposServico() != null) {
            var tiposEntities = domain.getTiposServico().stream()
                    .map(tipo -> tipoServicoMapper.toEntity(tipo, entity)) // associação explícita
                    .toList();
            entity.setTiposservicos(tiposEntities);
        }

        return entity;
    }

    public CategoriaServico toDomain(CategoriaServicoEntity entity) {
        if (entity == null) return null;

        // Reconstrói a categoria SEM os tipos ainda
        CategoriaServico categoria = CategoriaServico.reconstruir(
                entity.getId(),
                entity.getNome(),
                entity.getDescricao(),
                entity.getIcon(),
                entity.getCor(),
                entity.getOrdem(),
                entity.isEstado(),
                Identificador.from(entity.getCategoriaUuid()),
                null, // vamos adicionar os tipos depois
                entity.getCodigo()
        );

        if (entity.getTiposservicos() != null) {
            List<TipoServico> tipos = entity.getTiposservicos().stream()
                    .map(tipo -> tipoServicoMapper.toDomainWithCategoria(tipo, categoria))
                    .toList();
            categoria.getTiposServico().addAll(tipos);
        }

        return categoria;
    }
}

package cv.igrp.simple.configuracoes.infrastructure.persistence.repositories;

import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServicoFilter;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import cv.igrp.simple.configuracoes.infrastructure.mappers.CategoriaMapper;
import cv.igrp.simple.configuracoes.infrastructure.mappers.TipoServicoMapper;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriaServicoEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.TipoServicoEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.repository.TipoServicoEntityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TipoServicoRepositoryImpl implements TipoServicoRepository {

    private final TipoServicoEntityRepository jpaTipoServicoEntityRepository;

    private final TipoServicoMapper tipoServicoMapper;
    private final CategoriaMapper categoriaMapper;

    public TipoServicoRepositoryImpl(TipoServicoEntityRepository jpaTipoServicoEntityRepository, TipoServicoMapper tipoServicoMapper, CategoriaMapper categoriaMapper) {
        this.jpaTipoServicoEntityRepository = jpaTipoServicoEntityRepository;
        this.tipoServicoMapper = tipoServicoMapper;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public TipoServico save(TipoServico tipoServico) {
        // var tipoServicoEntity = tipoServicoMapper.toEntity(tipoServico);

        var categoriaEntity = categoriaMapper.toEntity(tipoServico.getCategoria());

        var tipoServicoEntity = tipoServicoMapper.toEntity(tipoServico, categoriaEntity);

        var saved = jpaTipoServicoEntityRepository.save(tipoServicoEntity);

        return tipoServicoMapper.toDomainWithCategoria(saved, tipoServico.getCategoria());

        //return tipoServicoMapper.toDomain(tipoServicoEntity);
    }

    @Override
    public Optional<TipoServico> findById(Integer id) {

        return jpaTipoServicoEntityRepository.findById(id)
                .map(tipoServicoMapper::toDomain);
    }

    @Override
    public Optional<TipoServico> findByUuId(UUID id) {
        return jpaTipoServicoEntityRepository.findByTipoServicoUuid(id)
                .map(entity -> {
                    var categoria = categoriaMapper.toDomain(entity.getCategoriaId()); // mapear categoria associada
                    return tipoServicoMapper.toDomainWithCategoria(entity, categoria);
                });
    }


    @Override
    public Optional<TipoServico> findByCodigo(String codigo) {
        return jpaTipoServicoEntityRepository.findByCodigo(codigo)
                .map(tipoServicoMapper::toDomain);
    }

    @Override
    public List<TipoServico> getAll(TipoServicoFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber(),
                filter.getPageSize()
        );

        Specification<TipoServicoEntity> spec = (root, query, cb) -> cb.conjunction();

        if (filter.getNome() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%")
            );
        }

        if (filter.getCodigo() != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("codigo"), filter.getCodigo())
            );
        }

        var page = jpaTipoServicoEntityRepository.findAll(spec, pageable);

        return page.getContent().stream()
                .map(entity -> {
                    var categoria = categoriaMapper.toDomain(entity.getCategoriaId());
                    return tipoServicoMapper.toDomainWithCategoria(entity, categoria);
                })
                .collect(Collectors.toList());
    }

}

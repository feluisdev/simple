package cv.igrp.simple.configuracoes.infrastructure.persistence.repositories;

import cv.igrp.simple.configuracoes.domain.models.CategoriaFilter;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import cv.igrp.simple.configuracoes.infrastructure.mappers.CategoriaMapper;
import cv.igrp.simple.configuracoes.infrastructure.persistence.entity.CategoriaServicoEntity;
import cv.igrp.simple.configuracoes.infrastructure.persistence.repository.CategoriaServicoEntityRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CategoriaServicoRepositoryImpl implements CategoriaServicoRepository {

    private final CategoriaServicoEntityRepository jpaCategoriaServicoEntityRepository;

    private final CategoriaMapper categoriaMapper;

    public CategoriaServicoRepositoryImpl(CategoriaServicoEntityRepository jpaCategoriaServicoEntityRepository, CategoriaMapper categoriaMapper) {
        this.jpaCategoriaServicoEntityRepository = jpaCategoriaServicoEntityRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaServico save(CategoriaServico categoriaServico) {

        var categoriaServicoEntity = categoriaMapper.toEntity(categoriaServico);
        jpaCategoriaServicoEntityRepository.save(categoriaServicoEntity);

        return categoriaMapper.toDomain(categoriaServicoEntity);

    }

    @Override
    public Optional<CategoriaServico> findById(Integer categoriaId) {
        return jpaCategoriaServicoEntityRepository.findById(categoriaId)
                .map(categoriaMapper::toDomain);
    }

    @Override
    public Optional<CategoriaServico> findByUuId(UUID categoriaId) {
        return jpaCategoriaServicoEntityRepository.findByCategoriaUuid(categoriaId)
                .map(categoriaMapper::toDomain);
    }

    @Override
    public Optional<CategoriaServico> findWithTiposByCategoriaUuid(UUID categoriaId) {
        return jpaCategoriaServicoEntityRepository.findWithTiposByCategoriaUuid(categoriaId)
                .map(categoriaMapper::toDomain);
    }

    @Override
    public List<CategoriaServico> getAll(CategoriaFilter filter) {

        var pageable = PageRequest.of(
                filter.getPageNumber(),
                filter.getPageSize());

        Specification<CategoriaServicoEntity> spec = (root, query, cb) -> cb.conjunction();

        if (filter.getNome()!=null) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("nome")), "%" + filter.getNome().toLowerCase() + "%")
            );
        }

        if (filter.getCodigo() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("codigo"), filter.getCodigo()));
        }
       var page = jpaCategoriaServicoEntityRepository.findAll(spec, pageable);

        return page.getContent().stream()
                .map(categoriaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CategoriaServico> findByCodigo(String codigo) {
        return jpaCategoriaServicoEntityRepository.findByCodigo(codigo)
                .map(categoriaMapper::toDomain);
    }
}

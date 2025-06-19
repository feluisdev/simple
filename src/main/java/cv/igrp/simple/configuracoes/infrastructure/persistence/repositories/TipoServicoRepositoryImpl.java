package cv.igrp.simple.configuracoes.infrastructure.persistence.repositories;

import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import cv.igrp.simple.configuracoes.infrastructure.mappers.TipoServicoMapper;
import cv.igrp.simple.configuracoes.infrastructure.persistence.repository.TipoServicoEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TipoServicoRepositoryImpl implements TipoServicoRepository {

    private final TipoServicoEntityRepository jpaTipoServicoEntityRepository;

    private final TipoServicoMapper tipoServicoMapper;

    public TipoServicoRepositoryImpl(TipoServicoEntityRepository jpaTipoServicoEntityRepository, TipoServicoMapper tipoServicoMapper) {
        this.jpaTipoServicoEntityRepository = jpaTipoServicoEntityRepository;
        this.tipoServicoMapper = tipoServicoMapper;
    }

    @Override
    public TipoServico save(TipoServico tipoServico) {
        var tipoServicoEntity = tipoServicoMapper.toEntity(tipoServico);

        jpaTipoServicoEntityRepository.save(tipoServicoEntity);

        return tipoServicoMapper.toDomain(tipoServicoEntity);
    }

    @Override
    public Optional<TipoServico> findById(Integer id) {
        return Optional.empty();
    }
}

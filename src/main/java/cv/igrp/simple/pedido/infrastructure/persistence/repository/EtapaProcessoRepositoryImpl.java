package cv.igrp.simple.pedido.infrastructure.persistence.repository;

import cv.igrp.simple.pedido.domain.models.EtapaProcesso;
import cv.igrp.simple.pedido.domain.repository.EtapaProcessoRepository;
import cv.igrp.simple.pedido.infrastructure.mappers.EtapaProcessoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class EtapaProcessoRepositoryImpl implements EtapaProcessoRepository {

    private final EtapaProcessoEntityRepository jpaEtapaProcessoEntityRepository;

    private final EtapaProcessoMapper etapaProcessoMapper;

    public EtapaProcessoRepositoryImpl(EtapaProcessoEntityRepository jpaEtapaProcessoEntityRepository, EtapaProcessoMapper etapaProcessoMapper) {
        this.jpaEtapaProcessoEntityRepository = jpaEtapaProcessoEntityRepository;
        this.etapaProcessoMapper = etapaProcessoMapper;
    }

    @Override
    public EtapaProcesso getById(Integer id) {
         var entity= jpaEtapaProcessoEntityRepository.findById(id)
                 .orElseThrow();

         return etapaProcessoMapper.toDomain(entity);
    }

    @Override
    public EtapaProcesso save(EtapaProcesso etapaProcesso) {

        var entity = jpaEtapaProcessoEntityRepository
                .save(etapaProcessoMapper.toEntity(etapaProcesso));

        return etapaProcessoMapper.toDomain(entity);
    }
}

package cv.igrp.simple.configuracoes.infrastructure.persistence.repository;

import cv.igrp.simple.configuracoes.domain.models.Combo;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoReadRepository;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.TipoServicoEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoServicoReadRepositoryImpl implements TipoServicoReadRepository {

    private final TipoServicoEntityRepository tipoServicoEntityRepository;

    public TipoServicoReadRepositoryImpl(TipoServicoEntityRepository tipoServicoEntityRepository) {
        this.tipoServicoEntityRepository = tipoServicoEntityRepository;
    }

    @Override
    public List<Combo<String>> getTipoServicoAtivos() {
        return tipoServicoEntityRepository.findByEstadoTrue()
                .stream()
                .map(tipo -> new Combo<>(
                        tipo.getNome(),
                        Identificador.from(tipo.getTipoServicoUuid()).getStringValor()
                ))
                .toList();
    }
}

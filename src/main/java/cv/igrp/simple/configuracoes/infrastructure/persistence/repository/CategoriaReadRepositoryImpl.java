package cv.igrp.simple.configuracoes.infrastructure.persistence.repository;

import cv.igrp.simple.configuracoes.domain.models.Combo;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaReadRepository;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import cv.igrp.simple.shared.infrastructure.persistence.repository.CategoriaServicoEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriaReadRepositoryImpl implements CategoriaReadRepository {

    private final CategoriaServicoEntityRepository categoriaServicoEntityRepository;

    public CategoriaReadRepositoryImpl(CategoriaServicoEntityRepository categoriaServicoEntityRepository) {
        this.categoriaServicoEntityRepository = categoriaServicoEntityRepository;
    }

    @Override
    public List<Combo<String>> getCategoriasAtivas() {
        return categoriaServicoEntityRepository.findByEstadoTrue()
                .stream()
                .map(cat -> new Combo<>(
                        cat.getNome(),
                        Identificador.from(cat.getCategoriaUuid()).getStringValor()
                ))
                .toList();

    }
}

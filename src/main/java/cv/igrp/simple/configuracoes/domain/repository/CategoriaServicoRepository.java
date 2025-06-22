package cv.igrp.simple.configuracoes.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.CategoriaFilter;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaServicoRepository {

    CategoriaServico save(CategoriaServico categoriaServico);

    Optional<CategoriaServico> findById(Integer categoriaId);

    Optional<CategoriaServico> findByUuId(UUID categoriaId);

    List<CategoriaServico> getAll(CategoriaFilter filter);

    Optional<CategoriaServico> findByCodigo(String codigo);


}

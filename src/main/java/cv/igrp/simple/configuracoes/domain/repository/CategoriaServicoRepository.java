package cv.igrp.simple.configuracoes.domain.repository;

import cv.igrp.simple.configuracoes.domain.models.CategoriaFilter;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import org.springframework.data.domain.Page; // Mantido se Page for usado na implementação, senão pode ser removido se getAll retornar List

import java.util.List;
import java.util.Optional;

public interface CategoriaServicoRepository {

    CategoriaServico save(CategoriaServico categoriaServico);

    Optional<CategoriaServico> findById(Integer categoriaId);

    Optional<CategoriaServico> findByCodigo(String codigo);

    List<CategoriaServico> getAll(CategoriaFilter filter); // Assumindo que Page não é estritamente necessário aqui para a interface de domínio
}

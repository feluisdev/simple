package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.simple.configuracoes.application.constants.Estado;
import cv.igrp.simple.configuracoes.application.dto.ListaCategoriaDTO;
import cv.igrp.simple.configuracoes.application.dto.WrapperListaCategoriaServicoDTO;
import cv.igrp.simple.configuracoes.domain.models.CategoriaFilter;
import cv.igrp.simple.configuracoes.domain.models.CategoriaServico;
import cv.igrp.simple.configuracoes.domain.repository.CategoriaServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.data.domain.Page; // Idealmente o repositório retornaria Page
import org.springframework.data.domain.PageImpl; // Para mock, se necessário
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaCategoriaServicoQueryHandler implements QueryHandler<ListaCategoriaServicoQuery, ResponseEntity<WrapperListaCategoriaServicoDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListaCategoriaServicoQueryHandler.class);

    private final CategoriaServicoRepository categoriaServicoRepository;

    public ListaCategoriaServicoQueryHandler(CategoriaServicoRepository categoriaServicoRepository) {
        this.categoriaServicoRepository = categoriaServicoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<WrapperListaCategoriaServicoDTO> handle(ListaCategoriaServicoQuery query) {
        LOGGER.info("Listando categorias de serviço com query: {}", query);

        var filter = new CategoriaFilter();
        filter.setNome(query.getNome());
        filter.setCodigo(query.getCodigo());
        filter.setEstado(query.getAtivo()); // Mapeia 'ativo' do query para 'estado' do filter
        filter.setPageNumber(query.getPageNumber());
        filter.setPageSize(query.getPageSize());

        // Idealmente, o método getAll do repositório lidaria com Pageable e retornaria Page<CategoriaServico>
        // Por ora, vamos assumir que ele retorna List<CategoriaServico> e a paginação é feita aqui ou na camada de persistência
        // Se o repositório já retornar Page<CategoriaServico>, o código abaixo seria mais direto.
        List<CategoriaServico> categoriasList = categoriaServicoRepository.getAll(filter);

        // Simulação de paginação se o repositório não suportar Page diretamente e getAll retornar toda a lista.
        // Isto NÃO é eficiente para grandes datasets. O ideal é que o BD faça a paginação.
        PageRequest pageable = PageRequest.of(query.getPageNumber(), query.getPageSize());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), categoriasList.size());
        List<CategoriaServico> pagedList = categoriasList.subList(start, end);
        Page<CategoriaServico> categoriasPage = new PageImpl<>(pagedList, pageable, categoriasList.size());


        List<ListaCategoriaDTO> listaDTO = categoriasPage.getContent().stream()
                .map(categoria -> {
                    var dto = new ListaCategoriaDTO();
                    dto.setId(categoria.getId());
                    dto.setNome(categoria.getNome());
                    dto.setCodigo(categoria.getCodigo());
                    // O campo 'estado' no ListaCategoriaDTO espera String ("A" ou "I")
                    dto.setEstado(categoria.isEstado() ? Estado.ATIVO.getCode() : Estado.INATIVO.getCode());
                    // CategoriaUuid não está no ListaCategoriaDTO
                    return dto;
                })
                .collect(Collectors.toList());

        var wrapper = new WrapperListaCategoriaServicoDTO();
        wrapper.setContent(listaDTO);
        wrapper.setPageNumber(categoriasPage.getNumber());
        wrapper.setPageSize(categoriasPage.getSize());
        wrapper.setTotalElements(categoriasPage.getTotalElements());
        wrapper.setTotalPages(categoriasPage.getTotalPages());
        // Outros campos do PageDTO como first, last, empty podem ser setados se necessário

        return ResponseEntity.ok(wrapper);
    }
}
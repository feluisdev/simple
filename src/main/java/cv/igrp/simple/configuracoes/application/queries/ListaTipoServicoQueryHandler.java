package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.simple.configuracoes.application.constants.Estado;
import cv.igrp.simple.configuracoes.application.dto.ListaTipoServicoDTO;
import cv.igrp.simple.configuracoes.application.dto.WrapperListaTipoServicoDTO;
import cv.igrp.simple.configuracoes.domain.models.TipoServico;
import cv.igrp.simple.configuracoes.domain.models.TipoServicoFilter;
import cv.igrp.simple.configuracoes.domain.repository.TipoServicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListaTipoServicoQueryHandler implements QueryHandler<ListaTipoServicoQuery, ResponseEntity<WrapperListaTipoServicoDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListaTipoServicoQueryHandler.class);

    private final TipoServicoRepository tipoServicoRepository;

    public ListaTipoServicoQueryHandler(TipoServicoRepository tipoServicoRepository) {
        this.tipoServicoRepository = tipoServicoRepository;
    }

    @IgrpQueryHandler
    public ResponseEntity<WrapperListaTipoServicoDTO> handle(ListaTipoServicoQuery query) {
        LOGGER.info("Listando tipos de serviço com query: {}", query);

        var filter = new TipoServicoFilter();
        filter.setNome(query.getNome());
        filter.setCodigo(query.getCodigo());
        filter.setEstado(query.getAtivo());
        filter.setCategoriaId(query.getCategoriaId());
        filter.setPageNumber(query.getPageNumber());
        filter.setPageSize(query.getPageSize());

        // Assim como em Categoria, idealmente o repositório retorna Page<TipoServico>
        List<TipoServico> tiposServicoList = tipoServicoRepository.getAll(filter);

        PageRequest pageable = PageRequest.of(query.getPageNumber(), query.getPageSize());
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), tiposServicoList.size());
        List<TipoServico> pagedList = tiposServicoList.subList(start, end);
        Page<TipoServico> tiposServicoPage = new PageImpl<>(pagedList, pageable, tiposServicoList.size());

        List<ListaTipoServicoDTO> listaDTO = tiposServicoPage.getContent().stream()
                .map(ts -> {
                    var dto = new ListaTipoServicoDTO();
                    dto.setId(ts.getId());
                    // dto.setTipoServicoId(ts.getTipoServicoUuid().toString()); // Se necessário, mas parece redundante com id
                    dto.setCodigo(ts.getCodigo());
                    dto.setNome(ts.getNome());
                    if (ts.getCategoria() != null) {
                        dto.setCategoria(ts.getCategoria().getNome()); // Nome da categoria
                    }
                    dto.setEstado(ts.isEstado() ? Estado.ATIVO.getCode() : Estado.INATIVO.getCode());
                    return dto;
                })
                .collect(Collectors.toList());

        var wrapper = new WrapperListaTipoServicoDTO();
        wrapper.setContent(listaDTO);
        wrapper.setPageNumber(tiposServicoPage.getNumber());
        wrapper.setPageSize(tiposServicoPage.getSize());
        wrapper.setTotalElements(tiposServicoPage.getTotalElements());
        wrapper.setTotalPages(tiposServicoPage.getTotalPages());

        return ResponseEntity.ok(wrapper);
    }
}
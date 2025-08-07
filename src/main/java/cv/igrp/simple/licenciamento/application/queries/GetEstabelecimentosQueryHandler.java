package cv.igrp.simple.licenciamento.application.queries;

import cv.igrp.simple.licenciamento.application.dto.WrapperListaEstabelecimentoDTO;
import cv.igrp.simple.licenciamento.domain.filter.EstabelecimentoFilter;
import cv.igrp.simple.licenciamento.domain.models.Estabelecimento;
import cv.igrp.simple.licenciamento.domain.repository.EstabelecimentoRepository;
import cv.igrp.simple.licenciamento.infrastructure.mappers.EstabelecimentoMapper;
import cv.igrp.simple.shared.application.constants.Estado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.simple.licenciamento.application.dto.EstabelecimentoResponseDTO;

import java.util.List;

@Component
public class GetEstabelecimentosQueryHandler implements QueryHandler<GetEstabelecimentosQuery, ResponseEntity<WrapperListaEstabelecimentoDTO>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetEstabelecimentosQueryHandler.class);

    private final EstabelecimentoRepository estabelecimentoRepository;
    private final EstabelecimentoMapper estabelecimentoMapper;

    public GetEstabelecimentosQueryHandler(EstabelecimentoRepository estabelecimentoRepository, EstabelecimentoMapper estabelecimentoMapper) {

        this.estabelecimentoRepository = estabelecimentoRepository;
        this.estabelecimentoMapper = estabelecimentoMapper;
    }

    @IgrpQueryHandler
    public ResponseEntity<WrapperListaEstabelecimentoDTO> handle(GetEstabelecimentosQuery query) {
        var filter = EstabelecimentoFilter.builder()
                .nome(query.getNome())
                .gerente(query.getGerente())
                .estado(query.getEstado() != null ? Estado.valueOf(query.getEstado()) : null)
                .pageNumber(Integer.parseInt(query.getPagina()))
                .pageSize(Integer.parseInt(query.getTamanho()))
                .build();


        List<Estabelecimento> estabelecimentos = estabelecimentoRepository.findAll(filter);

        var contentDTO = estabelecimentos.stream()
                .map(estabelecimentoMapper::toLigthDTO)
                .toList();

        var response = new WrapperListaEstabelecimentoDTO();
        response.setContent(contentDTO);
        response.setPageNumber(filter.getPageNumber());
        response.setPageSize(filter.getPageSize());
        response.setTotalElements((long) contentDTO.size());

        return ResponseEntity.ok(response);
    }

}
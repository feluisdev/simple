package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.simple.configuracoes.application.dto.ConfiguracoesResponseDTO;
import cv.igrp.framework.core.domain.QueryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListaDeConfiguracoesQueryHandler implements QueryHandler<ListaDeConfiguracoesQuery, Page<ConfiguracoesResponseDTO>> {


    @Override
    public Page<ConfiguracoesResponseDTO> handle(ListaDeConfiguracoesQuery query) {

        return null;
    }
}
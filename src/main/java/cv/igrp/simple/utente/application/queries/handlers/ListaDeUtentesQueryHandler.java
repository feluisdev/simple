package cv.igrp.simple.utente.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.infrastructure.mappers.UtenteMapper;
import cv.igrp.simple.utente.application.queries.filters.FiltroUtente;
import cv.igrp.simple.utente.application.queries.queries.ListaDeUtentesQuery;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.shared.infrastructure.persistence.repository.UtenteEntityRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListaDeUtentesQueryHandler implements QueryHandler<ListaDeUtentesQuery, ResponseEntity<Page<UtenteResponseDTO>>> {

    private final UtenteMapper utenteMapper;

    private final UtenteEntityRepository utenteRepository;

    private final FiltroUtente filtroUtente;

    public ListaDeUtentesQueryHandler(UtenteMapper utenteMapper, UtenteEntityRepository utenteRepository, FiltroUtente filtroUtente) {

        this.utenteMapper = utenteMapper;
        this.utenteRepository = utenteRepository;
        this.filtroUtente = filtroUtente;
    }

    @IgrpQueryHandler
    public ResponseEntity<Page<UtenteResponseDTO>> handle(ListaDeUtentesQuery query) {
        // TODO: Implement the query handling logic here
        Specification<UtenteEntity> specFiltro = filtroUtente.aplicarFiltros(query);

        Page<UtenteEntity> listaUtentePag = utenteRepository.findAll(specFiltro, query.getPageable());

        Page<UtenteResponseDTO> responsePage = listaUtentePag.map(u ->
                utenteMapper.toUtenteResponseDTO(u)
        );

        return ResponseEntity.ok(responsePage);

    }

}
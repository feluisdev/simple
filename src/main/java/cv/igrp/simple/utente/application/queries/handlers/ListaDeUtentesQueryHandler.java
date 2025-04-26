package cv.igrp.simple.utente.application.queries.handlers;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.application.mapper.UtenteMapper;
import cv.igrp.simple.utente.application.queries.filters.FiltroUtente;
import cv.igrp.simple.utente.application.queries.queries.ListaDeUtentesQuery;
import cv.igrp.simple.utente.domain.models.Utente;
import cv.igrp.simple.utente.infrastructure.persistence.UtenteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ListaDeUtentesQueryHandler implements QueryHandler<ListaDeUtentesQuery, ResponseEntity<Page<UtenteResponseDTO>>> {

    private final UtenteMapper utenteMapper;

    private final UtenteRepository utenteRepository;

    private final FiltroUtente filtroUtente;

    public ListaDeUtentesQueryHandler(UtenteMapper utenteMapper, UtenteRepository utenteRepository, FiltroUtente filtroUtente) {

        this.utenteMapper = utenteMapper;
        this.utenteRepository = utenteRepository;
        this.filtroUtente = filtroUtente;
    }

    @IgrpQueryHandler
    public ResponseEntity<Page<UtenteResponseDTO>> handle(ListaDeUtentesQuery query) {
        // TODO: Implement the query handling logic here
        Specification<Utente> specFiltro = filtroUtente.aplicarFiltros(query);

        Page<Utente> listaUtentePag = utenteRepository.findAll(specFiltro, query.getPageable());

        Page<UtenteResponseDTO> responsePage = listaUtentePag.map(u ->
                utenteMapper.toUtenteResponseDTO(u)
        );

        return ResponseEntity.ok(responsePage);

    }

}
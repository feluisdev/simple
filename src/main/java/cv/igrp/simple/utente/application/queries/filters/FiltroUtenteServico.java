package cv.igrp.simple.utente.application.queries.filters;

import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteServicoEntity;
import cv.igrp.simple.utente.application.queries.queries.ListaServicosUtenteQuery;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FiltroUtenteServico {

    public Specification<UtenteServicoEntity> aplicarFiltros(ListaServicosUtenteQuery query) {
        Specification<UtenteServicoEntity> spec = (root, query1, cb) -> cb.conjunction(); // inicializa com "true"
        Integer utenteId = Integer.parseInt(query.getUtenteId());

        spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("utenteId").get("id"), utenteId));

        if (query.getTipo() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("objetoTipo"), query.getTipo()));
        }

        if (query.getDataInicio() != null) {
            LocalDate dataInicio = parseDate(query.getDataInicio());
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dataInicio"), dataInicio));
        }

        if (query.getDataFIm() != null) {
            LocalDate dataFim = parseDate(query.getDataFIm());
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("dataFim"), dataFim));
        }

        return spec;
    }

    private LocalDate parseDate(String date) {
        if (date == null || date.isEmpty()) return null;
        return LocalDate.parse(date);
    }

}

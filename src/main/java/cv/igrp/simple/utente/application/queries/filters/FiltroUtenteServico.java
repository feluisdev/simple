package cv.igrp.simple.utente.application.queries.filters;

import cv.igrp.simple.utente.application.queries.queries.ListaServicosUtenteQuery;
import cv.igrp.simple.utente.domain.models.UtenteServicoEntity;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FiltroUtenteServico {

    public Specification<UtenteServicoEntity> aplicarFiltros(ListaServicosUtenteQuery query) {
        Specification<UtenteServicoEntity> spec = Specification.where(null);
        Integer utenteId = Integer.parseInt(query.getUtenteId());

        spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("utenteId").get("id"), utenteId));

        if (query.getTipo() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("objetoTipo"), query.getTipo()));
        }

      /*  if (query.getEstado() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("estado"), query.getEstado())); // Supondo que "estado" existe
        }*/

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

    // Método auxiliar para converter a string de data para LocalDate
    private LocalDate parseDate(String date) {
        if (date == null || date.isEmpty()) return null;
        return LocalDate.parse(date);
    }

}

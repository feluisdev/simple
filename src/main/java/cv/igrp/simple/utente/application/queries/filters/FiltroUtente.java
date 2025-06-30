package cv.igrp.simple.utente.application.queries.filters;

import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.utente.application.queries.queries.ListaDeUtentesQuery;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class FiltroUtente {

    public Specification<UtenteEntity> aplicarFiltros(ListaDeUtentesQuery query) {
        Specification<UtenteEntity> spec = Specification.where(null);

        if (query.getTipo() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("tipo"), query.getTipo()));
        }

        if (query.getNumeroUtente() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("nrUtente"), query.getNumeroUtente()));
        }

        if (query.getNome() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("nome"), "%" + query.getNome() + "%"));
        }

        if (query.getNif() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("nif"), query.getNif()));
        }

        if (query.getDocumento() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("documento"), query.getDocumento()));
        }

        if (query.getEstado() != null) {
            spec = spec.and((root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("estado"), query.getEstado()));
        }

        return spec;
    }
}

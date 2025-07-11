package cv.igrp.simple.pedidos.infrastructure.mappers;

import cv.igrp.simple.pedidos.domain.models.Utente;
import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import org.springframework.stereotype.Component;

@Component("utenteMapper2")
public class UtenteMapper {

    public Utente toDomain(UtenteEntity entity) {
        if (entity == null) {
            return null;
        }
        return Utente.reconstruir(entity.getId(), entity.getNome(), entity.getNumero());
    }

    public UtenteEntity toEntity(Utente domain) {

        var entity = new UtenteEntity();
        if (domain.getId()!=null)
         entity.setId(domain.getId());

        entity.setNome(domain.getNome());
        entity.setNumero(domain.getNumero());
        return entity;
    }
}

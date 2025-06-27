package cv.igrp.simple.utente.application.mapper;

import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.application.dto.CriarUtenteDTO;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import cv.igrp.simple.utente.domain.models.Utente;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UtenteMapper {


    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UtenteResponseDTO toUtenteResponseDTO(Utente utente){

        if (utente == null) {
            return null;
        }

        UtenteResponseDTO dto = new UtenteResponseDTO();
        dto.setId(utente.getId());
        dto.setTipoUtente(utente.getTipoUtente());
        dto.setNome(utente.getNome());
        dto.setNrUtente(utente.getNrUtente());
        dto.setNif(utente.getNif());
        dto.setBi(utente.getBi());
        dto.setNomeMae(utente.getNomeMae());
        dto.setNomePai(utente.getNomePai()); // Esse campo parece não estar presente na entidade — confirme se existe
        // Verificar se dataNascimento é null antes de chamar toString()
        LocalDate dataNascimento = utente.getDataNascimento();
        dto.setDataNascimento(dataNascimento != null ? dataNascimento.toString() : null);
        dto.setEstado(utente.getEstado());
        dto.setMorada(utente.getMorada());
        dto.setTelefone(utente.getTelefone());
        dto.setEmail(utente.getEmail());
        dto.setCxPostal(utente.getCxPostal());
        return dto;

    }

    public Utente toUtente(CriarUtenteDTO dto){
        Utente utente = new Utente();
        utente.setNome(dto.getNome());
        utente.setBi(dto.getBi());
        utente.setNif(dto.getNif());
        utente.setEstado(Estado.ATIVO);
        utente.setTelefone(dto.getTelefone());
        utente.setMorada(dto.getMorada());
        utente.setTipoUtente(dto.getTipoUtente());
        utente.setNomePai(dto.getNomePai());
        utente.setNome(dto.getNome());
        utente.setNomeMae(dto.getNomeMae());
        utente.setDataNascimento(dto.getDataNascimento());
        utente.setEmail(dto.getEmail());
        utente.setCxPostal(dto.getCxPostal());

        return utente;
    }

}

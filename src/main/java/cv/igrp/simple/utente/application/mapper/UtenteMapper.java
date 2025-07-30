package cv.igrp.simple.utente.application.mapper;

import cv.igrp.simple.shared.infrastructure.persistence.entity.UtenteEntity;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.application.dto.CriarUtenteDTO;
import cv.igrp.simple.utente.application.dto.UpdateUtenteDTO;
import cv.igrp.simple.utente.application.dto.UtenteResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class UtenteMapper {


    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public UtenteResponseDTO toUtenteResponseDTO(UtenteEntity utente){

        if (utente == null) {
            return null;
        }

        UtenteResponseDTO dto = new UtenteResponseDTO();
        dto.setId(utente.getId());
        dto.setTipoUtente(utente.getTipoUtente());
        dto.setNome(utente.getNome());
        dto.setNumero(utente.getNumero());
        dto.setNif(utente.getNif());
        dto.setTipoIdentificacao(utente.getTipoIdentificacao());
        dto.setIdentificacao(utente.getIdentificacao());
        dto.setNomeMae(utente.getNomeMae());
        dto.setNomePai(utente.getNomePai()); // Esse campo parece não estar presente na entidade — confirme se existe
        // Verificar se dataNascimento é null antes de chamar toString()
        LocalDate dataNascimento = utente.getDataNascimento();
        dto.setDataNascimento(dataNascimento != null ? dataNascimento.toString() : null);
        dto.setEstado(utente.getEstado().getDescription());
        dto.setEndereco(utente.getEndereco());
        dto.setTelefone(utente.getTelefone());
        dto.setEmail(utente.getEmail());
        dto.setCaixaPostal(utente.getCaixaPostal());
        dto.setDepartamentoResponsavel(utente.getDepartamentoResponsavel());
        dto.setTelemovel(utente.getTelemovel());
        dto.setGenero(utente.getGenero());
        dto.setNacionalidade(utente.getNacionalidade());

        return dto;
    }

    public UtenteEntity toUtente(CriarUtenteDTO dto){
        UtenteEntity utente = new UtenteEntity();
        utente.setNome(dto.getNome());
        utente.setTipoIdentificacao(dto.getTipoIdentificacao());
        utente.setNif(dto.getNif());
        utente.setEstado(Estado.ATIVO);
        utente.setTelefone(dto.getTelefone());
        utente.setEndereco(dto.getEndereco());
        utente.setTipoUtente(dto.getTipoUtente());
        utente.setNomePai(dto.getNomePai());
        utente.setNome(dto.getNome());
        utente.setNomeMae(dto.getNomeMae());
        utente.setDataNascimento(dto.getDataNascimento());
        utente.setEmail(dto.getEmail());
        utente.setCaixaPostal(dto.getCaixaPostal());
        utente.setDepartamentoResponsavel(dto.getDepartamentoResponsavel());
        utente.setIdentificacao(dto.getIdentificacao());
        utente.setTelemovel(dto.getTelemovel());
        utente.setGenero(dto.getGenero());
        utente.setNacionalidade(dto.getNacionalidade());

        return utente;
    }


    public UtenteEntity toUpdateUtente(UpdateUtenteDTO dto) {
        UtenteEntity utente = new UtenteEntity();
        utente.setNome(dto.getNome());
        utente.setTelefone(dto.getTelefone());
        utente.setEmail(dto.getEmail());
        utente.setEndereco(dto.getEndereco());
        utente.setDataNascimento(dto.getDataNascimento());
        utente.setIdentificacao(dto.getIdentificacao());
        utente.setTipoIdentificacao(dto.getTipoIdentificacao());
        utente.setCaixaPostal(dto.getCaixaPostal());
        utente.setDepartamentoResponsavel(dto.getDepartamentoResponsavel());
        utente.setTipoUtente(dto.getTipoUtente());
        utente.setNomeMae(dto.getNomeMae());
        utente.setNomePai(dto.getNomePai());
        utente.setNif(dto.getNif());
        utente.setTelemovel(dto.getTelemovel());
        utente.setGenero(dto.getGenero());
        utente.setNacionalidade(dto.getNacionalidade());

        return utente;
     }

}

package cv.igrp.simple.utente.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.application.constants.GeneroTipo;
import cv.igrp.simple.utente.application.constants.TipoIdentificacao;
import cv.igrp.simple.utente.application.constants.TipoUtente;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class UpdateUtenteDTO {

  
  
  @NotNull(message = "The field <tipoUtente> is required.")
  
  private TipoUtente tipoUtente;
  @NotBlank(message = "The field <nome> is required.")
	@Size(min = 3, message = "The field length <nome> must be at least 3 characters.")
  
  private String nome;
  @NotBlank(message = "The field <nif> is required.")
  
  private String nif;
  @NotNull(message = "The field <tipoIdentificacao> is required.")
  
  private TipoIdentificacao tipoIdentificacao;

  @NotNull(message = "The field <estado> is required.")
  
  private Estado estado;

  @NotBlank(message = "The field <identificacao> is required.")
  
  private String identificacao;
  
  
  private String nomeMae;
  
  
  private String nomePai;
  
  
  private LocalDate dataNascimento;
  @NotBlank(message = "The field <telefone> is required.")
  
  private String telefone;
  @NotBlank(message = "The field <endereco> is required.")
  
  private String endereco;
  @Email(message = "Invalid email format for field <email>.")
  
  private String email;
  
  
  private String caixaPostal;
  
  
  private String telemovel;
  
  
  private String departamentoResponsavel;
  
  
  private GeneroTipo genero;
  
  
  private String nacionalidade;
  

}
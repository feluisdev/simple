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
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class UtenteResponseDTO {

  
  
  private Integer id;
  @NotBlank(message = "The field <nome> is required.")
  
  private String nome;
  
  
  private TipoUtente tipoUtente;
  
  
  private String numero;
  
  
  private String nif;
  
  
  private String identificacao;
  
  
  private String nomeMae;
  
  
  private String nomePai;
  
  
  private String dataNascimento;
  
  
  private Estado estado;
  
  
  private String telefone;
  
  
  private String email;
  
  
  private String endereco;
  
  
  private String createdAt;
  
  
  private String updatedAt;
  
  
  private String caixaPostal;
  
  
  private String departamentoResponsavel;
  
  
  private TipoIdentificacao tipoIdentificacao;
  
  
  private GeneroTipo genero;
  
  
  private String nacionalidade;
  
  
  private String telemovel;

}
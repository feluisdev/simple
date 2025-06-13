package cv.igrp.simple.utente.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.utente.application.constants.Estado;
import cv.igrp.simple.utente.application.constants.TipoIdentificacao;
import cv.igrp.simple.utente.application.constants.TipoUtente;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class UpdateUtenteDTO {

  
  
  private TipoUtente tipoUtente;
  
  
  private TipoIdentificacao tipoIdentificacao;
  
  
  private String identificacao;
  @NotBlank(message = "The field <nome> is required.")
  
  private String nome;
  
  
  private String endereco;
  
  
  private String telefone;
  @Email(message = "Invalid email format for field <email>.")
  
  private String email;
  
  
  private String nif;
  
  
  private LocalDate dataNascimento;
  
  
  private Estado estado;
  
  
  private String nomeMae;
  
  
  private String nomePai;
  
  
  private String caixaPostal;
  
  
  private String departamentoResponsavel;

}
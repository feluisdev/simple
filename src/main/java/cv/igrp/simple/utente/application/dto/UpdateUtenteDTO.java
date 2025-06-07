package cv.igrp.simple.utente.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class UpdateUtenteDTO {

  @NotBlank(message = "The field <nome> is required.")
  
  private String nome;
  
  
  private String morada;
  
  
  private String telefone;
  @Email(message = "Invalid email format for field <email>.")
  
  private String email;
  
  
  private String nomeMae;
  
  
  private String nomePai;
  
  
  private String nif;
  
  
  private String bi;
  
  
  private String tipoUtente;
  
  
  private String cxPostal;
  
  
  private LocalDate dataNascimento;

}
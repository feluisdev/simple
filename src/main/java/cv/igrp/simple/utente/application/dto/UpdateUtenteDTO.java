package cv.igrp.simple.utente.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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

  private String cxPostal;

  private String nomeMae;

  private String nomePai;

  private String nif;

  private String bi;

  @NotBlank(message = "The field <tipoUtente> is required.")
  private String tipoUtente;

}
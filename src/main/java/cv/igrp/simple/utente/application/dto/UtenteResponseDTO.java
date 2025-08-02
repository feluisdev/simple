/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

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
public class UtenteResponseDTO  {

  
  
  private Integer id ;
  @NotBlank(message = "The field <nome> is required")
  
  private String nome ;
  
  
  private String tipoUtente ;
  
  
  private String tipoUtenteDesc ;
  
  
  private String numero ;
  
  
  private String nif ;
  
  
  private String identificacao ;
  
  
  private String nomeMae ;
  
  
  private String nomePai ;
  
  
  private LocalDate dataNascimento ;
  
  
  private String estado ;
  
  
  private String telefone ;
  
  
  private String email ;
  
  
  private String morada ;
  
  
  private String createdAt ;
  
  
  private String updatedAt ;
  
  
  private String caixaPostal ;
  
  
  private String departamentoResponsavel ;
  
  
  private String tipoIdentificacao ;
  
  
  private String tipoIdentificacaoDesc ;
  
  
  private String genero ;
  
  
  private String generoDesc ;
  
  
  private String nacionalidade ;
  
  
  private String telemovel ;
  
  
  private String endereco ;

}
/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.licenciamento.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


@IgrpDTO
public class LicencaResponseLigthDTO  {

  
  
  private String idLicenca ;
  
  
  private String alvara ;
  
  
  private LocalDate dataInicioLicenca ;
  
  
  private LocalDate dataFimLicenca ;
  
  
  private LocalDate dataRenovacaoLicenca ;
  
  
  private String designacao ;
  
  
  private LocalTime horarioInicioFuncionamento ;
  
  
  private LocalTime horarioFimFuncionamento ;
  
  
  private String estadoLicenca ;
  
  
  private String estadoLicencaDesc ;
  
  
  private String estabelecimentoId ;
  
  
  private String idUtente ;
  
  
  private String nomeUtente ;
  
  
  private String numeroUtente ;

}
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

@Data
@NoArgsConstructor
@AllArgsConstructor


@IgrpDTO
public class LicencaResponseDTO  {

  
  
  private String idLicenca ;
  
  
  private String alvara ;
  
  
  private LocalDate dataInicioLicenca ;
  
  
  private LocalDate dataFimLicenca ;
  
  
  private LocalDate dataRenovacaoLicenca ;
  
  
  private String horarioFuncionamento ;
  
  
  private String designacao ;
  
  
  private String estadoLicenca ;
  
  
  private String estadoLicencaDesc ;
  
  
  private String idEstabelecimento ;
  
  
  private String idUtente ;

}
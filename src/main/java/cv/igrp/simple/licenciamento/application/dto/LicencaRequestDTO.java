/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.licenciamento.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import cv.igrp.framework.stereotype.IgrpDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.shared.application.constants.EstadoLicenca;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor


@IgrpDTO
public class LicencaRequestDTO  {

  
  
  private String alvara ;
  
  
  private LocalDate dataInicioLicenca ;
  
  
  private LocalDate dataFimLicenca ;
  
  
  private LocalDate dataRenovacaoLicenca ;

  @Schema(type = "string", format = "time", example = "08:30:00")
  private LocalTime horarioInicioFuncionamento ;

  @Schema(type = "string", format = "time", example = "08:30:00")
  private LocalTime horarioFimFuncionamento ;
  
  
  private String designacao ;
  
  
  private EstadoLicenca estadoLicenca ;
  
  
  private String idEstabelecimento ;
  
  
  private String idUtente ;

}
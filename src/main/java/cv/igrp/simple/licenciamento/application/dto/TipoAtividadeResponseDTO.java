/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.licenciamento.application.dto;

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
public class TipoAtividadeResponseDTO  {

  
  
  private String tipoAtividadeId ;
  
  
  private String nome ;
  
  
  private String codigo ;
  
  
  private String descricao ;
  
  
  private String estado ;
  
  
  private String estadoDesc ;

}
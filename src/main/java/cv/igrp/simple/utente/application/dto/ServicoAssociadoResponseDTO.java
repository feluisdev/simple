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
public class ServicoAssociadoResponseDTO {

  
  
  private Integer idUtente;
  
  
  private Integer idServico;

}
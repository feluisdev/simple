package cv.igrp.simple.configuracoes.application.dto;

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
public class ListaTipoServicoDTO {

  
  
  private Integer id;
  
  
  private String tipoServicoId;
  
  
  private String codigo;
  
  
  private String nome;
  
  
  private String categoria;
  
  
  private String estado;

}
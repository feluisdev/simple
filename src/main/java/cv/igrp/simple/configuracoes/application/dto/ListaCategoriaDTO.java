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
public class ListaCategoriaDTO {

  
  
  private Integer id;
  
  
  private String categoriaId;
  
  
  private String nome;
  
  
  private String codigo;
  
  
  private String estado;

}
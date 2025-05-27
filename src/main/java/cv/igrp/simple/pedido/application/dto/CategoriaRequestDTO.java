package cv.igrp.simple.pedido.application.dto;

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
public class CategoriaRequestDTO {

  
  
  private String nome;
  
  
  private String descricao;
  
  
  private String icone;
  
  
  private String cor;
  
  
  private Integer ordem;

}
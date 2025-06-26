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
public class StatusPedidoResponseDTO {

  
  
  private Integer id;
  
  
  private String codigo;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private String cor;
  
  
  private String icone;
  
  
  private boolean fim;
  
  
  private boolean ativo;

}
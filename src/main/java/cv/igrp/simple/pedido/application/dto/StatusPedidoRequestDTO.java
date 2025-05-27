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
public class StatusPedidoRequestDTO {

  
  
  private String codigo;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private String cor;
  
  
  private Integer ordem;
  
  
  private String icone;
  
  
  private boolean visivelPortal;

}
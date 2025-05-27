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
public class TipoPedidoRequestDTO {

  
  
  private String codigo;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private Integer prazoEstimado;
  
  
  private Integer valorBase;
  
  
  private boolean requerVistoria;
  
  
  private boolean requerAnaliseTecnica;
  
  
  private boolean requerAprovacao;
  
  
  private boolean disponivelPortal;
  
  
  private Integer categoriaId;

}
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
public class EtapaProcessoRequestDTO {

  
  
  private Integer tipoPedidoId;
  
  
  private String codigo;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private Integer ordem;
  
  
  private Integer tempoEstimado;
  
  
  private boolean requerDocumento;
  
  
  private boolean requerPagamento;
  
  
  private boolean requerAprovacao;
  
  
  private Integer etapaAnteriorId;

}
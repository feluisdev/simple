package cv.igrp.simple.pedidos.application.dto;

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
public class EtapasPedidosResponseDTO {

  
  
  private Integer id;
  
  
  private String codigo;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private Integer ordem;
  
  
  private Integer tipoServicoId;
  
  
  private String tipoServicoNome;
  
  
  private Integer tempoEstimado;
  
  
  private boolean requerAprovacao;
  
  
  private boolean ativo;

}
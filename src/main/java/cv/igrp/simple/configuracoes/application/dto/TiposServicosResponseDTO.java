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
public class TiposServicosResponseDTO {

  
  
  private Integer id;
  
  
  private String categoriaId;
  
  
  private String codigo;
  
  
  private String nome;
  
  
  private String descricao;
  
  
  private Integer prazoEstimado;
  
  
  private Double valorBase;
  
  
  private boolean requerVistoria;
  
  
  private boolean requerAnaliseTec;
  
  
  private boolean requerAprovacao;
  
  
  private boolean disponivelPortal;
  
  
  private boolean ativo;

}
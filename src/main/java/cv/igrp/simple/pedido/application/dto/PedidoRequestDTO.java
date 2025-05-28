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
public class PedidoRequestDTO {

  
  
  private Integer tipoPedidoId;
  
  
  private Integer utenteId;
  
  
  private String origem;
  
  
  private String observacao;
  
  
  private Integer prioridade;

}
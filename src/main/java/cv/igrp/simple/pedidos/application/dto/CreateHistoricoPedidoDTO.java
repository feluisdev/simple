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
public class CreateHistoricoPedidoDTO {

  
  
  private Integer pedidoId;
  
  
  private Integer statusId;
  
  
  private Integer etapaId;
  
  
  private String observacao;

}
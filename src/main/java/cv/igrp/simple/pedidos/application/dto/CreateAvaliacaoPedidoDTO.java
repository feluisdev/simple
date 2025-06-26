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
public class CreateAvaliacaoPedidoDTO {

  
  
  private Integer pedidoId;
  
  
  private Integer nota;
  
  
  private String comentario;

}
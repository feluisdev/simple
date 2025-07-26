package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class AvaliacaoPedidoResponseDTO {

  
  
  private String avaliacaoPedidoId;
  
  
  private String pedidoId;
  
  
  private Integer nota;
  
  
  private String comentario;
  
  
  private LocalDate dataAvaliacao;
  
  
  private Integer userId;
  
  
  private String userNome;

}
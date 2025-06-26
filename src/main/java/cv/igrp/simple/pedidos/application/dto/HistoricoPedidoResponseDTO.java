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
public class HistoricoPedidoResponseDTO {

  
  
  private Integer id;
  
  
  private Integer pedidoId;
  
  
  private Integer statusId;
  
  
  private String statusNome;
  
  
  private String statusCor;
  
  
  private Integer etapaId;
  
  
  private String etapaNome;
  
  
  private Integer userId;
  
  
  private String userNome;
  
  
  private LocalDate dataRegistro;
  
  
  private String observacao;

}
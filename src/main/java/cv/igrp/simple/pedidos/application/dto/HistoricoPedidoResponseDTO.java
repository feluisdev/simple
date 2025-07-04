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
  
  
  private String pedidoId;
  
  
  private String statusId;
  
  
  private String statusNome;
  
  
  private String statusCor;
  
  
  private String etapaId;
  
  
  private String etapaNome;
  
  
  private Integer userId;
  
  
  private String userNome;
  
  
  private LocalDate dataRegistro;
  
  
  private String observacao;

}
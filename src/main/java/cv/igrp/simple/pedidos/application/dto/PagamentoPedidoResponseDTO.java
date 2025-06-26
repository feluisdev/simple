package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class PagamentoPedidoResponseDTO {

  
  
  private Integer id;
  
  
  private Integer pedidoId;
  
  
  private BigDecimal valor;
  
  
  private LocalDate dataPagamento;
  
  
  private String metodoPagamento;
  
  
  private String referenciaPagamento;
  
  
  private String status;
  
  
  private String observacao;

}
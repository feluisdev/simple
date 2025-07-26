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
public class PedidoResponseDTO {

  
  
  private Integer id;
  
  
  private String pedidoId;
  
  
  private String codigoAcompanhamento;
  
  
  private String tipoServicoId;
  
  
  private String tipoServicoNome;
  
  
  private String utenteId;
  
  
  private String utenteNome;
  
  
  private String etapaAtualId;
  
  
  private String etapaAtualNome;
  
  
  private String statusId;
  
  
  private String statusNome;
  
  
  private String statusCor;
  
  
  private LocalDate dataInicio;
  
  
  private LocalDate dataPrevisao;
  
  
  private LocalDate dataConclusao;
  
  
  private String observacoes;
  
  
  private BigDecimal valorTotal;
  
  
  private String origem;
  
  
  private String prioridade;
  
  
  private LocalDate dataCriacao;

}
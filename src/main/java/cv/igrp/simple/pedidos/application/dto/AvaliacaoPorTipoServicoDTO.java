package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class AvaliacaoPorTipoServicoDTO {

  
  
  private Integer tipoServicoId;
  
  
  private String tipoServicoNome;
  
  
  private BigDecimal mediaAvaliacao;
  
  
  private Integer totalAvaliacoes;

}
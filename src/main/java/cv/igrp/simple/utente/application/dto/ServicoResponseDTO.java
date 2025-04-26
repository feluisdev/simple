package cv.igrp.simple.utente.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.utente.application.constants.Estado;
import java.math.BigDecimal;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class ServicoResponseDTO {

  
  
  private Integer id;
  
  
  private String tipo;
  
  
  private String descricao;
  
  
  private String referencia;
  
  
  private String dataInicio;
  
  
  private String dataFim;
  
  
  private Estado estado;
  
  
  private BigDecimal valor;
  
  
  private Map<String, ?> detalhes;

}
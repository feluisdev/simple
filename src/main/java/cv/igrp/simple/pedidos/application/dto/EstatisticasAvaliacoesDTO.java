package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.pedidos.application.dto.AvaliacaoPorTipoServicoDTO;
import cv.igrp.simple.pedidos.application.dto.EvolucaoMensalAvaliacoesDTO;
import java.util.List;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class EstatisticasAvaliacoesDTO {

  
  
  private String mediaGeral;
  
  
  private String totalAvaliacoes;
  
  
  private Map<Integer, ?> distribuicaoNotas;
  
  @Valid
  private List<AvaliacaoPorTipoServicoDTO> avaliacoesPorTipoServico;
  
  @Valid
  private List<EvolucaoMensalAvaliacoesDTO> evolucaoMensal;

}
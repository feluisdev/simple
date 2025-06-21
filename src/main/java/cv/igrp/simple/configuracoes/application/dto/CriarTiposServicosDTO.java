package cv.igrp.simple.configuracoes.application.dto;

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
public class CriarTiposServicosDTO {

  @NotNull(message = "The field <categoriaId> is required.")
  
  private Integer categoriaId;
  @NotBlank(message = "The field <codigo> is required.")
	@Size(min = 2, message = "The field length <codigo> must be at least 2 characters.")
	@Size(max = 20, message = "The field length <codigo> cannot be more than 20 characters.")
  
  private String codigo;
  @NotBlank(message = "The field <nome> is required.")
	@Size(min = 3, message = "The field length <nome> must be at least 3 characters.")
	@Size(max = 100, message = "The field length <nome> cannot be more than 100 characters.")
  
  private String nome;
  
  
  private String descricao;
  
  
  private Integer prazoEstimado;
  
  
  private Double valorBase;
  
  
  private boolean requerVistoria;
  
  
  private boolean requerAnaliseTec;
  
  
  private boolean requerAprovacao;
  
  
  private boolean disponivelPortal;
  
  
  private boolean ativo;

}
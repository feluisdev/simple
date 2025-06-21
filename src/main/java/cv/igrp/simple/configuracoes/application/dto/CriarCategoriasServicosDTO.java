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
public class CriarCategoriasServicosDTO {

  @NotBlank(message = "The field <nome> is required.")
	@Size(min = 3, message = "The field length <nome> must be at least 3 characters.")
	@Size(max = 100, message = "The field length <nome> cannot be more than 100 characters.")
  
  private String nome;
  @NotBlank(message = "The field <codigo> is required.")
  
  private String codigo;
  
  
  private String descricao;
  
  
  private String icone;
  
  
  private String cor;
  
  
  private Integer ordem;
  
  
  private boolean ativo;

}
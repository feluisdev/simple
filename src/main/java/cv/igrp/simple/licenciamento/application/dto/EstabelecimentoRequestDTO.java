/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.simple.licenciamento.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor


@IgrpDTO
public class EstabelecimentoRequestDTO  {

  
  
  private String nome ;
  
  
  private String gerente ;
  
  
  private String descricao ;
  
  
  private String endereco ;
  
  
  private String telefone ;
  
  
  private String email ;
  
  
  private String nif ;
  
  
  private boolean flagVistoria ;
  
  
  private boolean licRetalho ;
  
  
  private String tipoAtividadeId ;
  
  
  private List<String> classesId = new ArrayList<>();

}
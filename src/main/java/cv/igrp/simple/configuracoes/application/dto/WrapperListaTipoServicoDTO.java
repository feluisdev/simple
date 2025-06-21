package cv.igrp.simple.configuracoes.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.configuracoes.application.dto.ListaTipoServicoDTO;
import cv.igrp.simple.shared.application.dto.PageDTO;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class WrapperListaTipoServicoDTO extends PageDTO{

  
  @Valid
  private ListaTipoServicoDTO content;

}
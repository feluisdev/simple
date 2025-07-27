package cv.igrp.simple.configuracoes.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.configuracoes.application.dto.ListaCategoriaDTO;
import cv.igrp.simple.shared.application.dto.PageDTO;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class WrapperListaCategoriaServicoDTO extends PageDTO{

  
  @Valid
  private List<ListaCategoriaDTO> content;

}
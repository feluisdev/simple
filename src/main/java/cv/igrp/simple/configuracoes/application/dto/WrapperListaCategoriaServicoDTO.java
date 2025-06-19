package cv.igrp.simple.configuracoes.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.configuracoes.application.dto.CategoriasServicosResponseDTO;
import cv.igrp.simple.shared.application.dto.PageDTO;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class WrapperListaCategoriaServicoDTO extends PageDTO{

  
  @Valid
  private List<CategoriasServicosResponseDTO> content;

}
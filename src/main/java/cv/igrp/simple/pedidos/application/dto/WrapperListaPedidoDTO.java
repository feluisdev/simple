package cv.igrp.simple.pedidos.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.pedidos.application.dto.PedidoResponseDTO;
import cv.igrp.simple.shared.application.dto.PageDTO;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor

@IgrpDTO
public class WrapperListaPedidoDTO extends PageDTO{

  
  @Valid
  private List<PedidoResponseDTO> content;

}
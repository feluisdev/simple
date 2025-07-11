package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.simple.configuracoes.application.dto.CriarCategoriasServicosDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoriaServicoCommand implements Command {

  
  private CriarCategoriasServicosDTO criarcategoriasservicos;
  @NotBlank(message = "The field <categoriaServicoId> is required.")
  private String categoriaServicoId;

}
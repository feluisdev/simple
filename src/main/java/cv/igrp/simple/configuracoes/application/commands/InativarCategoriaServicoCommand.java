package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InativarCategoriaServicoCommand implements Command {

  @NotNull(message = "ID da Categoria de Serviço não pode ser nulo.")
  private Integer categoriaServicoId;

  // Construtor para facilitar a criação nos controllers
  public InativarCategoriaServicoCommand(Integer categoriaServicoId) {
    this.categoriaServicoId = categoriaServicoId;
  }
}
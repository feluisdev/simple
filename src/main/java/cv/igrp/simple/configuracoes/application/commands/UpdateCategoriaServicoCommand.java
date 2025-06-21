package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.configuracoes.application.dto.UpdateCategoriasServicosDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoriaServicoCommand implements Command {

  @NotNull(message = "ID da Categoria de Serviço não pode ser nulo.")
  private Integer categoriaServicoId; // Assumindo que o ID é Integer

  @Valid // Para validar os campos do DTO
  @NotNull(message = "Os dados de atualização não podem ser nulos.")
  private UpdateCategoriasServicosDTO updateDto;

  // Construtor para facilitar a criação nos controllers
  public UpdateCategoriaServicoCommand(Integer categoriaServicoId, UpdateCategoriasServicosDTO updateDto) {
    this.categoriaServicoId = categoriaServicoId;
    this.updateDto = updateDto;
  }
}
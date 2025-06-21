package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.simple.configuracoes.application.dto.UpdateTiposServicosDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTipoServicoCommand implements Command {

  @NotNull(message = "ID do Tipo de Serviço não pode ser nulo.")
  private Integer tipoServicoId;

  @Valid // Para validar os campos do DTO
  @NotNull(message = "Os dados de atualização não podem ser nulos.")
  private UpdateTiposServicosDTO updateDto;

  // Construtor para facilitar a criação nos controllers
  public UpdateTipoServicoCommand(Integer tipoServicoId, UpdateTiposServicosDTO updateDto) {
    this.tipoServicoId = tipoServicoId;
    this.updateDto = updateDto;
  }
}
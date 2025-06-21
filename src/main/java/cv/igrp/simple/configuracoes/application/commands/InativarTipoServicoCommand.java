package cv.igrp.simple.configuracoes.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InativarTipoServicoCommand implements Command {

  @NotNull(message = "ID do Tipo de Serviço não pode ser nulo.")
  private Integer tipoServicoId;

  // Construtor para facilitar a criação nos controllers
  public InativarTipoServicoCommand(Integer tipoServicoId) {
    this.tipoServicoId = tipoServicoId;
  }
}
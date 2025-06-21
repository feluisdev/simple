package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTipoServicoQuery implements Query {

  @NotNull(message = "ID do Tipo de Serviço não pode ser nulo.")
  private Integer tipoServicoId;

  // Construtor para facilitar a criação nos controllers
  public GetTipoServicoQuery(Integer tipoServicoId) {
    this.tipoServicoId = tipoServicoId;
  }
}
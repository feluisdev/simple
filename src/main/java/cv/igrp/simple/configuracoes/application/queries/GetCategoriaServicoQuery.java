package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoriaServicoQuery implements Query {

  @NotNull(message = "ID da Categoria de Serviço não pode ser nulo.")
  private Integer categoriaServicoId;

  // Construtor para facilitar a criação nos controllers
  public GetCategoriaServicoQuery(Integer categoriaServicoId) {
    this.categoriaServicoId = categoriaServicoId;
  }
}
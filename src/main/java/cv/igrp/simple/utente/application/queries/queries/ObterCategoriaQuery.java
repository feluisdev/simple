package cv.igrp.simple.utente.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObterCategoriaQuery implements Query {

  @NotNull(message = "ID não pode ser nulo")
  private Integer id;

}
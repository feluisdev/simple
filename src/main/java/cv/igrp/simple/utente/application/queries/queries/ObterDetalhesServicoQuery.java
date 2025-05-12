package cv.igrp.simple.utente.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObterDetalhesServicoQuery implements Query {

  @NotNull(message = "The field <utenteId> is required.")
  private Integer utenteId;
  @NotNull(message = "The field <servicoId> is required.")
  private Integer servicoId;

}
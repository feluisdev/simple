package cv.igrp.simple.utente.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaServicosUtenteQuery implements Query {

  @NotBlank(message = "The field <Tipo> is required.")
  private String tipo;
  @NotBlank(message = "The field <Estado> is required.")
  private String estado;
  @NotBlank(message = "The field <dataInicio> is required.")
  private String dataInicio;
  @NotBlank(message = "The field <dataFIm> is required.")
  private String dataFIm;
  @NotBlank(message = "The field <utenteId> is required.")
  private String utenteId;
  @NotNull(message = "The field <pageable> is required.")
  private Pageable pageable;

}
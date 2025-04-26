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
public class ListaDeUtentesQuery implements Query {

  @NotBlank(message = "The field <tipo> is required.")
  private String tipo;
  @NotBlank(message = "The field <numeroUtente> is required.")
  private String numeroUtente;
  @NotBlank(message = "The field <nome> is required.")
  private String nome;
  @NotBlank(message = "The field <nif> is required.")
  private String nif;
  @NotBlank(message = "The field <documento> is required.")
  private String documento;
  @NotBlank(message = "The field <estado> is required.")
  private String estado;
  @NotNull(message = "The field <pageable> is required.")
  private Pageable pageable;

}
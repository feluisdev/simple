package cv.igrp.simple.configuracoes.application.queries;

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
public class ListaDeConfiguracoesQuery implements Query {

  @NotBlank(message = "The field <chave> is required.")
  private String chave;
  @NotBlank(message = "The field <grupo> is required.")
  private String grupo;
  @NotBlank(message = "The field <tipo> is required.")
  private String tipo;
  @NotBlank(message = "The field <estado> is required.")
  private String estado;
  @NotNull(message = "The field <pageable> is required.")
  private Pageable pageable;

}
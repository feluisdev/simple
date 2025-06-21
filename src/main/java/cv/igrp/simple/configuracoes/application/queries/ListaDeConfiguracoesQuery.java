package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDeConfiguracoesQuery implements Query {

  private String chave;
  private String grupo;
  private String tipo;
  private String estado;
  
  @NotNull(message = "The field <pageable> is required.")
  private Pageable pageable;

}
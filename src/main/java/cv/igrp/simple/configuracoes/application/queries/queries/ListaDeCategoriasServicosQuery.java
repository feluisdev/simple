package cv.igrp.simple.configuracoes.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDeCategoriasServicosQuery implements Query {

  private String nome;
  private Boolean ativo;
  
  @NotNull(message = "The field <pageable> is required.")
  private Pageable pageable;

}
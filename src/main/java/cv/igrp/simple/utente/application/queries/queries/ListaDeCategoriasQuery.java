package cv.igrp.simple.utente.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDeCategoriasQuery implements Query {

  private String nome;
  
  private String estado;
  
  @NotNull(message = "Pageable não pode ser nulo")
  private Pageable pageable;

}
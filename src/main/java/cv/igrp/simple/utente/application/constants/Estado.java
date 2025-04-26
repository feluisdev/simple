package cv.igrp.simple.utente.application.constants;

import lombok.Getter;

@Getter
public enum Estado {

  ATIVO(
    "ativo", 
    "indica que esta ativo"
  ),
    INATIVO(
    "inativo", 
    "indica que esta inativo"
  )
  ;

  private final String code;
  private final String description;

  Estado(String code, String description) {
    this.code = code;
    this.description = description;
  }

}

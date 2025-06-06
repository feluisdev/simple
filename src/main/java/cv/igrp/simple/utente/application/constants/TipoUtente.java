package cv.igrp.simple.utente.application.constants;

import lombok.Getter;

@Getter
public enum TipoUtente {

  CIDADAO(
    "CIDADAO", 
    "Cidadão"
  ),
    EMPRESA(
    "EMPRESA", 
    "Empresa"
  ),
    SERV_PUBLICO(
    "SERV_PUBLICO", 
    "Servidor Público"
  )
  ;

  private final String code;
  private final String description;

  TipoUtente(String code, String description) {
    this.code = code;
    this.description = description;
  }

}

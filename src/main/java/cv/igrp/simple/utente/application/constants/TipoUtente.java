package cv.igrp.simple.utente.application.constants;

import lombok.Getter;

@Getter
public enum TipoUtente {

  CIDADAO(
    "cidadao", 
    "Cidadão"
  ),
    EMPRESA(
    "empresa", 
    "Empresa"
  ),
    CAMARA_MUNICPAL(
    "camara", 
    "Câmara Municipal"
  )
  ;

  private final String code;
  private final String description;

  TipoUtente(String code, String description) {
    this.code = code;
    this.description = description;
  }

}

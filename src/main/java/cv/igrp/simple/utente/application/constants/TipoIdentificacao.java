package cv.igrp.simple.utente.application.constants;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum TipoIdentificacao {

  BI(
    "BI", "Bilhete de Indentidade"
  ),
    CNI(
    "CNI", "Cartão Nacional de Identificação"
  ),
    PEC(
    "PEC", "Passaporte"
  ),
    NIPC(
    "NIPC", "Numero de Identificação de Pessoa Coletiva"
  )
  ;

  private final String code;
  private final String description;

  TipoIdentificacao(String code, String description) {
    this.code = code;
    this.description = description;
  }

  /**
  * Pre-built maps for fast lookup.
  */
  private static final Map<String, TipoIdentificacao> CODE_MAP = Arrays.stream(values())
          .collect(Collectors.toMap(TipoIdentificacao::getCode, Function.identity()));

  /**
  * Attempts to find the enum value associated with the given code.
  * @param code The code to look up
  * @return An Optional containing the enum value if found, empty Optional otherwise
  */
  public static Optional<TipoIdentificacao> fromCode(String code) {
    return Optional.ofNullable(CODE_MAP.get(code));
  }

  /**
  * Finds the enum value associated with the given code or throws an exception if not found.
  * @param code The code to look up
  * @return The enum value for the given code
  * @throws IllegalArgumentException if no enum value exists for the given code
  */
  public static TipoIdentificacao fromCodeOrThrow(String code) {
    return fromCode(code).orElseThrow(() -> new IllegalArgumentException("Invalid TipoIdentificacao for this code: " + code));
  }

  /**
  * Returns a map of code to description.
  */
  public static Map<String, String> codeDescriptionMap() {
    return CODE_MAP.values().stream().collect(Collectors.toMap(TipoIdentificacao::getCode, TipoIdentificacao::getDescription));
  }

}

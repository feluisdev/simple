package cv.igrp.simple.configuracoes.application.constants;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Getter;

@Getter
public enum Estado {

  ATIVO(
    "ativo", "indica que esta ativo"
  ),
    INATIVO(
    "inativo", "indica que esta inativo"
  )
  ;

  private final String code;
  private final String description;

  Estado(String code, String description) {
    this.code = code;
    this.description = description;
  }

  /**
  * Pre-built maps for fast lookup.
  */
  private static final Map<String, Estado> CODE_MAP = Arrays.stream(values())
          .collect(Collectors.toMap(Estado::getCode, Function.identity()));

  /**
  * Attempts to find the enum value associated with the given code.
  * @param code The code to look up
  * @return An Optional containing the enum value if found, empty Optional otherwise
  */
  public static Optional<Estado> fromCode(String code) {
    return Optional.ofNullable(CODE_MAP.get(code));
  }

  /**
  * Finds the enum value associated with the given code or throws an exception if not found.
  * @param code The code to look up
  * @return The enum value for the given code
  * @throws IllegalArgumentException if no enum value exists for the given code
  */
  public static Estado fromCodeOrThrow(String code) {
    return fromCode(code).orElseThrow(() -> new IllegalArgumentException("Invalid Estado for this code: " + code));
  }

  /**
  * Returns a map of code to description.
  */
  public static Map<String, String> codeDescriptionMap() {
    return CODE_MAP.values().stream().collect(Collectors.toMap(Estado::getCode, Estado::getDescription));
  }

}
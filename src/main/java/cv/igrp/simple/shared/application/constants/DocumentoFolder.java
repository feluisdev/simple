package cv.igrp.simple.shared.application.constants;

import cv.igrp.framework.core.domain.IgrpEnum;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DocumentoFolder implements IgrpEnum<String> {

    UTENTE("Utente documents", "Utente documents")
    ;

    private final String code;
    private final String description;

    DocumentoFolder(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Pre-built maps for fast lookup.
     */
    private static final Map<String, DocumentoFolder> CODE_MAP = Arrays.stream(values())
            .collect(Collectors.toMap(DocumentoFolder::getCode, Function.identity()));

    /**
     * Attempts to find the enum value associated with the given code.
     * @param code The code to look up
     * @return An Optional containing the enum value if found, empty Optional otherwise
     */
    public static Optional<DocumentoFolder> fromCode(String code) {
        return Optional.ofNullable(CODE_MAP.get(code));
    }

    /**
     * Finds the enum value associated with the given code or throws an exception if not found.
     * @param code The code to look up
     * @return The enum value for the given code
     * @throws IllegalArgumentException if no enum value exists for the given code
     */
    public static DocumentoFolder fromCodeOrThrow(String code) {
        return fromCode(code).orElseThrow(() -> new IllegalArgumentException("Invalid DocumentoFolder for this code: " + code));
    }

    /**
     * Returns a map of code to description.
     */
    public static Map<String, String> codeDescriptionMap() {
        return CODE_MAP.values().stream().collect(Collectors.toMap(DocumentoFolder::getCode, DocumentoFolder::getDescription));
    }

}

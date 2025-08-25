package cv.igrp.simple.shared.domain.valueobject;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class Metadata {

    private final Map<String, Object> valores;

    private Metadata(Map<String, Object> valores) {
        if (valores == null) {
            this.valores = Collections.emptyMap();
        } else {
            // cria cópia defensiva para imutabilidade
            this.valores = Collections.unmodifiableMap(new HashMap<>(valores));
        }
    }

    /**
     * Factory para criar Metadata a partir de um Map
     */
    public static Metadata fromMap(Map<String, Object> map) {
        return new Metadata(map);
    }

    /**
     * Recupera um valor pela chave
     */
    public Object get(String chave) {
        return valores.get(chave);
    }

    /**
     * Verifica se contém determinada chave
     */
    public boolean contains(String chave) {
        return valores.containsKey(chave);
    }

    /**
     * Cria Metadata vazia
     */
    public static Metadata vazio() {
        return new Metadata(Collections.emptyMap());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Metadata that)) return false;
        return valores.equals(that.valores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valores);
    }

    @Override
    public String toString() {
        return valores.toString();
    }
}

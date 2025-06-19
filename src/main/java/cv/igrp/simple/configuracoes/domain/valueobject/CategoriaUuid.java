package cv.igrp.simple.configuracoes.domain.valueobject;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class CategoriaUuid {

    private final UUID value;

    private CategoriaUuid(UUID value) {
        this.value = Objects.requireNonNull(value, "UUID não pode ser nulo");
    }

    public static CategoriaUuid gerar() {
        return new CategoriaUuid(UUID.randomUUID());
    }

    public static CategoriaUuid from(UUID uuid) {
        return new CategoriaUuid(uuid);
    }



}

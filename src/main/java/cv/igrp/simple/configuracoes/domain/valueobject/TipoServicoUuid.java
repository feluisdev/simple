package cv.igrp.simple.configuracoes.domain.valueobject;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class TipoServicoUuid {

    private final UUID value;

    private TipoServicoUuid(UUID value) {
        this.value = Objects.requireNonNull(value, "UUID não pode ser nulo");
    }

    public static TipoServicoUuid gerar() {
        return new TipoServicoUuid(UUID.randomUUID());
    }

    public static TipoServicoUuid from(UUID uuid) {
        return new TipoServicoUuid(uuid);
    }

}

package cv.igrp.simple.configuracoes.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObterStatusPedidoQuery implements Query {
    @NotNull(message = "O ID é obrigatório")
    private Integer id;
}
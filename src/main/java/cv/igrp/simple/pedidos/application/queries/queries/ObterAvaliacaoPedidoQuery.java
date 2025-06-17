package cv.igrp.simple.pedidos.application.queries.queries;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import cv.igrp.framework.core.domain.Query;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObterAvaliacaoPedidoQuery implements Query {
    private Integer pedidoId;
}
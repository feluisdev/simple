package cv.igrp.simple.pedidos.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ListarHistoricoPedidoQuery implements Query {

    private final Integer pedidoId;
}
package cv.igrp.simple.pedidos.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ListarPagamentosPedidoQuery implements Query {

    private final Integer pedidoId;

}
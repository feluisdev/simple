package cv.igrp.simple.pedidos.application.queries.queries;

import cv.igrp.framework.core.domain.Query;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ListarPedidosQuery implements Query{

    private final String codigoAcompanhamento;
    private final Integer tipoServicoId;
    private final Integer cidadaoId;
    private final Integer userResponsavelId;
    private final Integer etapaAtualId;
    private final Integer statusId;
    private final LocalDateTime dataInicio;
    private final LocalDateTime dataInicioFim;
    private final LocalDateTime dataConclusao;
    private final LocalDateTime dataConclusaoFim;
    private final String origem;
    private final Integer prioridade;
    private final Integer page;
    private final Integer size;
    private final String sort;

}
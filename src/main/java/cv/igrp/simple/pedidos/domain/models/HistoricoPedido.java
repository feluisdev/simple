package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.configuracoes.domain.models.StatusPedido;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class HistoricoPedido {

    private Integer idDb;
    private Identificador historicoUuid;
    private Integer userId;
    private LocalDate dataRegistro;
    private String observacao;
    private Pedido pedido;
    private StatusPedido status;

    private HistoricoPedido(Integer idDb,
                            Identificador historicoUuid,
                            Integer userId,
                            LocalDate dataRegistro,
                            String observacao,
                            Pedido pedido,
                            StatusPedido status) {
        this.idDb = idDb;
        this.historicoUuid = historicoUuid;
        this.userId = userId;
        this.dataRegistro = dataRegistro;
        this.observacao = observacao;
        this.pedido = pedido;
        this.status = status;
    }

    public static HistoricoPedido criarNovo(String observacao, Pedido pedido, StatusPedido status) {
        Integer userId = 1; // todo resolve this later
        return new HistoricoPedido(null, Identificador.gerarNovo(), userId, LocalDate.now(), observacao, pedido, status);
    }

    public static HistoricoPedido reconstruir(Integer idDb,
                                              Identificador historicoUuid,
                                              Integer userId,
                                              LocalDate dataRegistro,
                                              String observacao,
                                              Pedido pedido,
                                              StatusPedido status) {
        return new HistoricoPedido(idDb, historicoUuid, userId, dataRegistro, observacao, pedido, status);
    }
}

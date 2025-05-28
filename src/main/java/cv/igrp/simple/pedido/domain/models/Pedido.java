package cv.igrp.simple.pedido.domain.models;

import java.time.LocalDate;

public class Pedido {

    private Integer id;
    private TipoPedido tipoPedido;
    private Integer utenteId;

    private EtapaProcesso etapaAtual; // setada internamente (primeira do tipo)
    private StatusPedido status;      // inicial

    private LocalDate dataInicio;
    private LocalDate dataPrevisao;
    private String observacao;
    private String origem;
    private Integer prioridade;

    public Pedido(TipoPedido tipoPedido, Integer utenteId, String origem, String observacao, Integer prioridade, StatusPedido status) {
        this.tipoPedido = tipoPedido;
        this.utenteId = utenteId;
        this.origem = origem;
        this.observacao = observacao;
        this.prioridade = prioridade;

        this.dataInicio = LocalDate.now();
        this.status = status;

        if (tipoPedido.getPrazoEstimado() != null) {
            this.dataPrevisao = LocalDate.now().plusDays(tipoPedido.getPrazoEstimado());
        }

       // this.etapaAtual = tipoPedido.getPrimeiraEtapa(); // lógica definida no TipoPedido
    }

}

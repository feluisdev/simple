package cv.igrp.simple.pedidos.application.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreateHistoricoPedidoDTO {

    @NotNull(message = "O ID do pedido é obrigatório")
    private UUID pedidoId;

    @NotNull(message = "O ID do status é obrigatório")
    private Integer statusId;

    private Integer etapaId;

    private String observacao;

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Integer getEtapaId() {
        return etapaId;
    }

    public void setEtapaId(Integer etapaId) {
        this.etapaId = etapaId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
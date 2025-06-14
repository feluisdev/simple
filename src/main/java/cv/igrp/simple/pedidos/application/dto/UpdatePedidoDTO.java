package cv.igrp.simple.pedidos.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class UpdatePedidoDTO {

    private UUID userResponsavelId;
    private Integer etapaAtualId;
    private Integer statusId;
    private LocalDateTime dataPrevisao;
    private LocalDateTime dataConclusao;
    private String observacoes;
    private BigDecimal valorTotal;
    private Integer prioridade;

    public UUID getUserResponsavelId() {
        return userResponsavelId;
    }

    public void setUserResponsavelId(UUID userResponsavelId) {
        this.userResponsavelId = userResponsavelId;
    }

    public Integer getEtapaAtualId() {
        return etapaAtualId;
    }

    public void setEtapaAtualId(Integer etapaAtualId) {
        this.etapaAtualId = etapaAtualId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public LocalDateTime getDataPrevisao() {
        return dataPrevisao;
    }

    public void setDataPrevisao(LocalDateTime dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
}
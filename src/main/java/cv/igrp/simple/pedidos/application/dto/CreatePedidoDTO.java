package cv.igrp.simple.pedidos.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.UUID;

public class CreatePedidoDTO {

    @NotNull(message = "O tipo de serviço é obrigatório")
    private Integer tipoServicoId;

    @NotNull(message = "O cidadão é obrigatório")
    private UUID cidadaoId;

    private String observacoes;

    private BigDecimal valorTotal;

    @NotBlank(message = "A origem é obrigatória")
    @Size(min = 2, max = 20, message = "A origem deve ter entre 2 e 20 caracteres")
    private String origem;

    @NotNull(message = "A prioridade é obrigatória")
    private Integer prioridade;

    public Integer getTipoServicoId() {
        return tipoServicoId;
    }

    public void setTipoServicoId(Integer tipoServicoId) {
        this.tipoServicoId = tipoServicoId;
    }

    public UUID getCidadaoId() {
        return cidadaoId;
    }

    public void setCidadaoId(UUID cidadaoId) {
        this.cidadaoId = cidadaoId;
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

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
}
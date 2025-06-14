package cv.igrp.simple.pedidos.application.dto;

import jakarta.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public class PedidoFilterDTO {

    private String codigoAcompanhamento;
    private Integer tipoServicoId;
    private UUID cidadaoId;
    private UUID userResponsavelId;
    private Integer etapaAtualId;
    private Integer statusId;
    private LocalDateTime dataInicio;
    private LocalDateTime dataInicioFim;
    private LocalDateTime dataConclusao;
    private LocalDateTime dataConclusaoFim;
    private String origem;
    private Integer prioridade;
    
    @NotNull(message = "A paginação é obrigatória")
    private Pageable pageable;

    public String getCodigoAcompanhamento() {
        return codigoAcompanhamento;
    }

    public void setCodigoAcompanhamento(String codigoAcompanhamento) {
        this.codigoAcompanhamento = codigoAcompanhamento;
    }

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

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataInicioFim() {
        return dataInicioFim;
    }

    public void setDataInicioFim(LocalDateTime dataInicioFim) {
        this.dataInicioFim = dataInicioFim;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public LocalDateTime getDataConclusaoFim() {
        return dataConclusaoFim;
    }

    public void setDataConclusaoFim(LocalDateTime dataConclusaoFim) {
        this.dataConclusaoFim = dataConclusaoFim;
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

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
package cv.igrp.simple.pedidos.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PedidoResponseDTO {

    private UUID id;
    private String codigoAcompanhamento;
    private Integer tipoServicoId;
    private String tipoServicoNome;
    private UUID cidadaoId;
    private String cidadaoNome;
    private UUID userCriacaoId;
    private String userCriacaoNome;
    private UUID userResponsavelId;
    private String userResponsavelNome;
    private Integer etapaAtualId;
    private String etapaAtualNome;
    private Integer statusId;
    private String statusNome;
    private String statusCor;
    private LocalDateTime dataInicio;
    private LocalDateTime dataPrevisao;
    private LocalDateTime dataConclusao;
    private String observacoes;
    private BigDecimal valorTotal;
    private String origem;
    private Integer prioridade;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public String getTipoServicoNome() {
        return tipoServicoNome;
    }

    public void setTipoServicoNome(String tipoServicoNome) {
        this.tipoServicoNome = tipoServicoNome;
    }

    public UUID getCidadaoId() {
        return cidadaoId;
    }

    public void setCidadaoId(UUID cidadaoId) {
        this.cidadaoId = cidadaoId;
    }

    public String getCidadaoNome() {
        return cidadaoNome;
    }

    public void setCidadaoNome(String cidadaoNome) {
        this.cidadaoNome = cidadaoNome;
    }

    public UUID getUserCriacaoId() {
        return userCriacaoId;
    }

    public void setUserCriacaoId(UUID userCriacaoId) {
        this.userCriacaoId = userCriacaoId;
    }

    public String getUserCriacaoNome() {
        return userCriacaoNome;
    }

    public void setUserCriacaoNome(String userCriacaoNome) {
        this.userCriacaoNome = userCriacaoNome;
    }

    public UUID getUserResponsavelId() {
        return userResponsavelId;
    }

    public void setUserResponsavelId(UUID userResponsavelId) {
        this.userResponsavelId = userResponsavelId;
    }

    public String getUserResponsavelNome() {
        return userResponsavelNome;
    }

    public void setUserResponsavelNome(String userResponsavelNome) {
        this.userResponsavelNome = userResponsavelNome;
    }

    public Integer getEtapaAtualId() {
        return etapaAtualId;
    }

    public void setEtapaAtualId(Integer etapaAtualId) {
        this.etapaAtualId = etapaAtualId;
    }

    public String getEtapaAtualNome() {
        return etapaAtualNome;
    }

    public void setEtapaAtualNome(String etapaAtualNome) {
        this.etapaAtualNome = etapaAtualNome;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusNome() {
        return statusNome;
    }

    public void setStatusNome(String statusNome) {
        this.statusNome = statusNome;
    }

    public String getStatusCor() {
        return statusCor;
    }

    public void setStatusCor(String statusCor) {
        this.statusCor = statusCor;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
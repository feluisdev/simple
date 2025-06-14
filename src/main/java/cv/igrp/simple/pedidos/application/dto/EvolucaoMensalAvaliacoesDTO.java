package cv.igrp.simple.pedidos.application.dto;

import java.math.BigDecimal;

public class EvolucaoMensalAvaliacoesDTO {

    private Integer mes;
    private Integer ano;
    private BigDecimal mediaAvaliacao;
    private Integer totalAvaliacoes;

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(BigDecimal mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public Integer getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(Integer totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }
}
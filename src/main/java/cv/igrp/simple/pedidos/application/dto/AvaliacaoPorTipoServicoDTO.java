package cv.igrp.simple.pedidos.application.dto;

import java.math.BigDecimal;

public class AvaliacaoPorTipoServicoDTO {

    private Integer tipoServicoId;
    private String tipoServicoNome;
    private BigDecimal mediaAvaliacao;
    private Integer totalAvaliacoes;

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
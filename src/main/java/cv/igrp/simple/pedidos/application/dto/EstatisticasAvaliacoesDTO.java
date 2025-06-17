package cv.igrp.simple.pedidos.application.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class EstatisticasAvaliacoesDTO {

    private BigDecimal mediaGeral;
    private Integer totalAvaliacoes;
    private Map<Integer, Integer> distribuicaoNotas;
    private List<AvaliacaoPorTipoServicoDTO> avaliacoesPorTipoServico;
    private List<EvolucaoMensalAvaliacoesDTO> evolucaoMensal;

    public BigDecimal getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(BigDecimal mediaGeral) {
        this.mediaGeral = mediaGeral;
    }

    public Integer getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    public void setTotalAvaliacoes(Integer totalAvaliacoes) {
        this.totalAvaliacoes = totalAvaliacoes;
    }

    public Map<Integer, Integer> getDistribuicaoNotas() {
        return distribuicaoNotas;
    }

    public void setDistribuicaoNotas(Map<Integer, Integer> distribuicaoNotas) {
        this.distribuicaoNotas = distribuicaoNotas;
    }

    public List<AvaliacaoPorTipoServicoDTO> getAvaliacoesPorTipoServico() {
        return avaliacoesPorTipoServico;
    }

    public void setAvaliacoesPorTipoServico(List<AvaliacaoPorTipoServicoDTO> avaliacoesPorTipoServico) {
        this.avaliacoesPorTipoServico = avaliacoesPorTipoServico;
    }

    public List<EvolucaoMensalAvaliacoesDTO> getEvolucaoMensal() {
        return evolucaoMensal;
    }

    public void setEvolucaoMensal(List<EvolucaoMensalAvaliacoesDTO> evolucaoMensal) {
        this.evolucaoMensal = evolucaoMensal;
    }
}
package cv.igrp.simple.pedidos.application.dto;

public class EtapasPedidosResponseDTO {

    private Integer id;
    private String codigo;
    private String nome;
    private String descricao;
    private Integer ordem;
    private Integer tipoServicoId;
    private String tipoServicoNome;
    private Integer tempoEstimado;
    private Boolean requerAprovacao;
    private Boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
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

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Integer tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Boolean getRequerAprovacao() {
        return requerAprovacao;
    }

    public void setRequerAprovacao(Boolean requerAprovacao) {
        this.requerAprovacao = requerAprovacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
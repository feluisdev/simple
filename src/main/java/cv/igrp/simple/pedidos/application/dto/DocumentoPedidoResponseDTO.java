package cv.igrp.simple.pedidos.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class DocumentoPedidoResponseDTO {

    private UUID id;
    private UUID pedidoId;
    private String nome;
    private String descricao;
    private String tipoDocumento;
    private String caminhoArquivo;
    private Integer tamanhoArquivo;
    private LocalDateTime dataUpload;
    private UUID userId;
    private String userNome;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getCaminhoArquivo() {
        return caminhoArquivo;
    }

    public void setCaminhoArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public Integer getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(Integer tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public LocalDateTime getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(LocalDateTime dataUpload) {
        this.dataUpload = dataUpload;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUserNome() {
        return userNome;
    }

    public void setUserNome(String userNome) {
        this.userNome = userNome;
    }
}
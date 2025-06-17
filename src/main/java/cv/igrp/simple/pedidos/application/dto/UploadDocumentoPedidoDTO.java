package cv.igrp.simple.pedidos.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class UploadDocumentoPedidoDTO {

    @NotNull(message = "O ID do pedido é obrigatório")
    private UUID pedidoId;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    private String descricao;

    @NotBlank(message = "O tipo de documento é obrigatório")
    @Size(min = 2, max = 50, message = "O tipo de documento deve ter entre 2 e 50 caracteres")
    private String tipoDocumento;

    @NotNull(message = "O arquivo é obrigatório")
    private MultipartFile arquivo;

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

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }
}
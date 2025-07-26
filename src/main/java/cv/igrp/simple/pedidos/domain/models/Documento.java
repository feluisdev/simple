package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Documento {


    private Integer idDb;
    private Identificador documentoUuid;
    private String nome;
    private String descricao;
    private String tipoDocumento;
    private String caminhoArquivo;
    private LocalDate dataUpload;
    private Pedido pedido;

    private Documento(Integer idDb,
                      Identificador documentoUuid,
                      String nome,
                      String descricao,
                      String tipoDocumento,
                      String caminhoArquivo,
                      LocalDate dataUpload,
                      Pedido pedido) {

        this.idDb = idDb;
        this.documentoUuid = Objects.requireNonNull(documentoUuid);
        this.nome = Objects.requireNonNull(nome);
        this.descricao = descricao;
        this.tipoDocumento = Objects.requireNonNull(tipoDocumento);
        this.caminhoArquivo = Objects.requireNonNull(caminhoArquivo);
        this.dataUpload = Objects.requireNonNull(dataUpload);
        this.pedido = Objects.requireNonNull(pedido);
    }

    public static Documento criarNovo(String nome,
                                      String descricao,
                                      String tipoDocumento,
                                      String caminhoArquivo,
                                      LocalDate dataUpload,
                                      Pedido pedido) {

        return new Documento(
                null,
                Identificador.gerarNovo(),
                nome,
                descricao,
                tipoDocumento,
                caminhoArquivo,
                dataUpload,
                pedido
        );
    }

    public static Documento reconstruir(Integer idDb,
                                        Identificador documentoUuid,
                                        String nome,
                                        String descricao,
                                        String tipoDocumento,
                                        String caminhoArquivo,
                                        LocalDate dataUpload,
                                        Pedido pedido) {

        return new Documento(
                idDb,
                documentoUuid,
                nome,
                descricao,
                tipoDocumento,
                caminhoArquivo,
                dataUpload,
                pedido
        );
    }


    public void atualizar(String nome, String descricao, String tipoDocumento, String caminhoArquivo) {
        this.nome = Objects.requireNonNull(nome);
        this.descricao = descricao;
        this.tipoDocumento = Objects.requireNonNull(tipoDocumento);
        this.caminhoArquivo = Objects.requireNonNull(caminhoArquivo);
    }

}

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
    private Integer tamanhoArquivo;
    private LocalDate dataUpload;
    private Integer userId;
    private Pedido pedido;

    private Documento(Integer idDb,
                      Identificador documentoUuid,
                      String nome,
                      String descricao,
                      String tipoDocumento,
                      String caminhoArquivo,
                      Integer tamanhoArquivo,
                      LocalDate dataUpload,
                      Integer userId,
                      Pedido pedido) {

        this.idDb = idDb;
        this.documentoUuid = Objects.requireNonNull(documentoUuid);
        this.nome = Objects.requireNonNull(nome);
        this.descricao = descricao;
        this.tipoDocumento = Objects.requireNonNull(tipoDocumento);
        this.caminhoArquivo = Objects.requireNonNull(caminhoArquivo);
        this.tamanhoArquivo = tamanhoArquivo;
        this.dataUpload = Objects.requireNonNull(dataUpload);
        this.userId = Objects.requireNonNullElse(userId, 1); // TODO: resolver userId
        this.pedido = Objects.requireNonNull(pedido);
    }

    public static Documento criarNovo(String nome,
                                      String descricao,
                                      String tipoDocumento,
                                      String caminhoArquivo,
                                      Integer tamanhoArquivo,
                                      LocalDate dataUpload,
                                      Pedido pedido) {

        return new Documento(
                null,
                Identificador.gerarNovo(),
                nome,
                descricao,
                tipoDocumento,
                caminhoArquivo,
                tamanhoArquivo,
                dataUpload,
                1, // TODO: resolver userId corretamente depois
                pedido
        );
    }

    public static Documento reconstruir(Integer idDb,
                                        Identificador documentoUuid,
                                        String nome,
                                        String descricao,
                                        String tipoDocumento,
                                        String caminhoArquivo,
                                        Integer tamanhoArquivo,
                                        LocalDate dataUpload,
                                        Integer userId,
                                        Pedido pedido) {

        return new Documento(
                idDb,
                documentoUuid,
                nome,
                descricao,
                tipoDocumento,
                caminhoArquivo,
                tamanhoArquivo,
                dataUpload,
                userId,
                pedido
        );
    }
}

package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Pagamento {


    private  Integer idDb;
    private final Identificador pagamentoUuid;
    private final LocalDate dataPagamento;
    private final String metodoPagamento;
    private final String referenciaPagamento;
    private final String status;
    private final String observacao;
    private final BigDecimal valor;
    private final Pedido pedido;

    private Pagamento(Integer idDb,
                      Identificador pagamentoUuid,
                      LocalDate dataPagamento,
                      String metodoPagamento,
                      String referenciaPagamento,
                      String status,
                      String observacao,
                      BigDecimal valor,
                      Pedido pedido) {

        this.idDb = idDb;
        this.pagamentoUuid = Objects.requireNonNull(pagamentoUuid);
        this.dataPagamento = Objects.requireNonNull(dataPagamento);
        this.metodoPagamento = Objects.requireNonNull(metodoPagamento);
        this.referenciaPagamento = referenciaPagamento;
        this.status = Objects.requireNonNull(status);
        this.observacao = observacao;
        this.valor = valor;
        this.pedido = Objects.requireNonNull(pedido);
    }

    public static Pagamento criarNovo(LocalDate dataPagamento,
                                      String metodoPagamento,
                                      String referenciaPagamento,
                                      String status,
                                      String observacao,
                                      BigDecimal valor,
                                      Pedido pedido) {

        return new Pagamento(
                null,
                Identificador.gerarNovo(),
                dataPagamento,
                metodoPagamento,
                referenciaPagamento,
                status,
                observacao,
                valor,
                pedido
        );
    }

    public static Pagamento reconstruir(Integer idDb,
                                        Identificador pagamentoUuid,
                                        LocalDate dataPagamento,
                                        String metodoPagamento,
                                        String referenciaPagamento,
                                        String status,
                                        String observacao,
                                        BigDecimal valor,
                                        Pedido pedido) {

        return new Pagamento(
                idDb,
                pagamentoUuid,
                dataPagamento,
                metodoPagamento,
                referenciaPagamento,
                status,
                observacao,
                valor,
                pedido
        );
    }
}

package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.shared.application.constants.StatusPagamento;
import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Pagamento {


    private  Integer idDb;
    private  Identificador pagamentoUuid;
    private  LocalDate dataPagamento;
    private  String metodoPagamento;
    private  String referenciaPagamento;
    private  StatusPagamento status;
    private  String observacao;
    private  BigDecimal valor;
    private final Pedido pedido;

    private Pagamento(Integer idDb,
                      Identificador pagamentoUuid,
                      LocalDate dataPagamento,
                      String metodoPagamento,
                      String referenciaPagamento,
                      StatusPagamento status,
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

    public static Pagamento criarNovo(
                                      String metodoPagamento,
                                      String referenciaPagamento,
                                      String observacao,
                                      BigDecimal valor,
                                      Pedido pedido) {

        return new Pagamento(
                null,
                Identificador.gerarNovo(),
                LocalDate.now(),
                metodoPagamento,
                referenciaPagamento,
                StatusPagamento.PAGO,
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
                                        StatusPagamento status,
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

    public void atualizar(String metodoPagamento,
                          String referenciaPagamento,
                          String observacao,
                          BigDecimal valor) {

        if (this.status == StatusPagamento.PAGO) {
            //todo resolve later
        }

        this.metodoPagamento = Objects.requireNonNull(metodoPagamento);
        this.referenciaPagamento = referenciaPagamento;
        this.observacao = observacao;
        this.valor = valor;
    }
}
